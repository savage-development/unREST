package com.savadev.unrest.config;

import com.savadev.unrest.config.properties.SecurityProperties;
import com.savadev.unrest.dao.file.JwkFileRepository;
import com.savadev.unrest.dao.file.JwkRepository;
import com.savadev.unrest.dao.file.JwkSetResourceLoader;
import com.savadev.unrest.service.security.CryptPasswordEncoder;
import com.savadev.unrest.service.security.SecurityService;
import com.savadev.unrest.service.security.UnrestSecurityService;
import com.savadev.unrest.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Flux;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    private final UserService userService;

    @Autowired
    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    //  @formatter:off
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(auth -> auth
                        .pathMatchers("/docker/**")
                            .hasAnyRole("ROOT", "DOCKER")
                        .pathMatchers("/security/jwk/rotate")
                            .hasRole("ROOT")
                        .anyExchange()
                            .authenticated())
                .httpBasic()
                    .authenticationManager(basicAuthenticationManager())
                .and()
                    .oauth2ResourceServer()
                        .jwt()
                            .authenticationManager(jwtAuthenticationManager())
                    .and()
                .and()
                    .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .build();
    }
    //  @formatter:on

    @Bean
    @ConfigurationProperties(prefix = "application.security")
    SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    ReactiveAuthenticationManager basicAuthenticationManager() {
        var manager = new UserDetailsRepositoryReactiveAuthenticationManager(userService);
        manager.setPasswordEncoder(passwordEncoder());
        return manager;
    }

    ReactiveAuthenticationManager jwtAuthenticationManager() {
        return new JwtReactiveAuthenticationManager(NimbusReactiveJwtDecoder
                .withJwkSource(signedJWT -> Flux.from(securityService().getJwk()))
                .jwsAlgorithm(SignatureAlgorithm.ES256)
                .build());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new CryptPasswordEncoder();
    }

    @Bean
    JwkSetResourceLoader jwkSetResourceLoader() {
        var props = securityProperties();
        return new JwkSetResourceLoader(props.getJwk());
    }

    @Bean
    JwkRepository jwkRepository() {
        return new JwkFileRepository(securityProperties(), jwkSetResourceLoader());
    }

    @Bean
    SecurityService securityService() {
        return new UnrestSecurityService(jwkRepository(), userService);
    }

}
