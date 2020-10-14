package com.savadev.unrest.dao.file;

import com.nimbusds.jose.jwk.JWK;
import reactor.core.publisher.Mono;

public interface JwkRepository {

    Mono<JWK> getJwk();

    Mono<JWK> rotate(JWK jwk);

}
