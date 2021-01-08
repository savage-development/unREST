package com.savadev.unrest.dao.file;

import com.fasterxml.jackson.databind.JsonNode;
import com.nimbusds.jose.jwk.JWK;
import com.savadev.unrest.dao.AbstractResourceLoader;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.text.ParseException;

public class JwkSetResourceLoader extends AbstractResourceLoader<JWK> {

    private final URI path;

    public JwkSetResourceLoader(URI path) {
        this.path = path;
    }

    @Override
    public Mono<JWK> load() {
        Jackson2JsonDecoder decoder = new Jackson2JsonDecoder();
        return decoder.decodeToMono(DataBufferUtils.read(getResource(path), new DefaultDataBufferFactory(), 4096),
                ResolvableType.forType(JsonNode.class), null, null)
                .flatMap(o -> {
                    try {
                        return Mono.just(JWK.parse(o.toString()));
                    } catch (ParseException e) {
                        return Mono.error(e);
                    }
                });
    }

}
