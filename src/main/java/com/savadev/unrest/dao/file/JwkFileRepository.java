package com.savadev.unrest.dao.file;

import com.nimbusds.jose.jwk.JWK;
import com.savadev.unrest.config.properties.SecurityProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
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
        return loader.load();
    }

    @Override
    public Mono<JWK> rotate(JWK jwk) {
        if (!Files.exists(Paths.get(properties.getJwk()))) {
            try {
                Files.createDirectory(Paths.get(properties.getJwk()).getParent());
            } catch (IOException e) {
                return Mono.error(e);
            }
        }
        return DataBufferUtils.write(DataBufferUtils.read(new ByteArrayResource(jwk.toJSONString().getBytes()),
                new DefaultDataBufferFactory(), 4096), Paths.get(properties.getJwk()))
                .thenReturn(jwk);
    }

}
