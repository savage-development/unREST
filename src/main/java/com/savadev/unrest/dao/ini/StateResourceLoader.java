package com.savadev.unrest.dao.ini;

import com.savadev.unrest.config.properties.StateProperties;
import com.savadev.unrest.dao.ResourceLoader;
import org.ini4j.Config;
import org.ini4j.Ini;
import org.springframework.core.io.FileSystemResource;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class StateResourceLoader implements ResourceLoader<Ini> {

    private final StateProperties properties;

    private final Executor executor;

    public StateResourceLoader(StateProperties properties, Executor executor) {
        this.properties = properties;
        this.executor = executor;
    }

    @Override
    public Mono<Ini> load(String resource) {
        return load(resource, null);
    }

    public Mono<Ini> load(String resource, Config config) {
        return Mono.fromFuture(CompletableFuture.supplyAsync(() -> {
            try {
                var ini = new Ini();
                if (config != null) {
                    ini.setConfig(config);
                }
                ini.load(new FileSystemResource(properties.getPath() + resource).getInputStream());
                return ini;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor));
    }

}
