package com.savadev.unrest.controller.security;

import com.savadev.unrest.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/security")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostMapping("/jwk/rotate")
    Mono<ResponseEntity<Void>> rotate() {
        return securityService.rotate().thenReturn(ResponseEntity.noContent().build());
    }

    @GetMapping("/jwt")
    Mono<ResponseEntity<String>> generateJwt(@RequestParam(value = "ttl", required = false) Duration ttl) {
        return securityService.generateJwt(ttl).map(jwt -> ResponseEntity.ok(jwt.serialize()));
    }

}
