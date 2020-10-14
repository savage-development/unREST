package com.savadev.unrest.service.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyOperation;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.savadev.unrest.dao.file.JwkRepository;
import com.savadev.unrest.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.NoSuchFileException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UnrestSecurityService implements SecurityService {

    private final JwkRepository jwkRepository;

    private final UserService userService;

    public UnrestSecurityService(JwkRepository jwkRepository, UserService userService) {
        this.jwkRepository = jwkRepository;
        this.userService = userService;
    }

    @Override
    public Mono<JWK> getJwk() {
        return jwkRepository.getJwk().onErrorResume(NoSuchFileException.class, e -> rotate());
    }

    @Override
    public Mono<JWK> rotate() {
        return createJwk().flatMap(jwkRepository::rotate);
    }

    @Override
    public Mono<JWT> generateJwt(Duration expires) {
        return ReactiveSecurityContextHolder.getContext().flatMap(securityContext -> Flux.fromIterable(securityContext.getAuthentication()
                .getAuthorities())
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet())
                .flatMap(roles -> createJwt(securityContext.getAuthentication(),
                        expires == null ? null : Instant.now().plus(expires), roles)));
    }

    protected Mono<JWK> createJwk() {
        Set<KeyOperation> ops = new HashSet<>();
        ops.add(KeyOperation.SIGN);
        ops.add(KeyOperation.VERIFY);
        try {
            return Mono.just(new ECKeyGenerator(Curve.P_256)
                    .keyUse(KeyUse.SIGNATURE)
                    .keyOperations(ops)
                    .generate());
        } catch (JOSEException ex) {
            return Mono.error(ex);
        }
    }

    protected Mono<JWT> createJwt(Authentication authentication, Instant expires, Set<String> roles) {
        return getJwk().flatMap(jwk -> {
            try {
                var jws = new SignedJWT(getHJwsHeader(), getClaims(authentication, expires, roles));
                jws.sign(new ECDSASigner(jwk.toECKey()));
                return Mono.just(jws);
            } catch (JOSEException e) {
                return Mono.error(e);
            }
        });
    }

    protected JWSHeader getHJwsHeader() {
        return new JWSHeader.Builder(JWSAlgorithm.ES256).build();
    }

    protected JWTClaimsSet getClaims(Authentication authentication, Instant expires, Set<String> roles) {
        var builder = new JWTClaimsSet.Builder()
                .subject(authentication.getName())
                .claim("roles", roles);
        if (expires != null) {
            builder.expirationTime(Date.from(expires));
        }
        return builder.build();
    }

}
