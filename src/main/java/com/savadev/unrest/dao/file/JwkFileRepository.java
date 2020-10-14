package com.savadev.unrest.dao.file;

import com.nimbusds.jose.jwk.JWK;
import com.savadev.unrest.config.properties.SecurityProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class JwkFileRepository implements JwkRepository {

    private final SecurityProperties properties;

    private final JwkSetResourceLoader loader;

    public JwkFileRepository(SecurityProperties properties, JwkSetResourceLoader loader) {
        this.properties = properties;
        this.loader = loader;
    }

    @Override
    public Mono<JWK> getJwk() {
        return loader.load(properties.getJwk());
    }

    @Override
    public Mono<JWK> rotate(JWK jwk) {
        try {
            return DataBufferUtils.write(DataBufferUtils.read(new ByteArrayResource(jwk.toJSONString().getBytes()),
                    new DefaultDataBufferFactory(), 4096), Paths.get(new URI("file:" + properties.getJwk())))
                    .thenReturn(jwk);
        } catch (URISyntaxException e) {
            return Mono.error(e);
        }
    }

}
