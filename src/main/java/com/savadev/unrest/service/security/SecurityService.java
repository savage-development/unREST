package com.savadev.unrest.service.security;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jwt.JWT;
import reactor.core.publisher.Mono;

import java.time.Duration;

public interface SecurityService {

    Mono<JWK> getJwk();

    Mono<JWK> rotate();

    Mono<JWT> generateJwt(Duration expires);

}
