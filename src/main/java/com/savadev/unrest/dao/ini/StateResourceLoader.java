package com.savadev.unrest.dao.ini;

import com.savadev.unrest.dao.AbstractResourceLoader;
import org.ini4j.Config;
import org.ini4j.Ini;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class StateResourceLoader extends AbstractResourceLoader<Ini> {

    private final URI path;

    private final Config config;

    private final Executor executor;

    public StateResourceLoader(URI path, Executor executor) {
        this(path, null, executor);
    }

    public StateResourceLoader(URI path, Config config, Executor executor) {
        this.path = path;
        this.config = config;
        this.executor = executor;
    }

    @Override
    public Mono<Ini> load() {
        return Mono.fromFuture(CompletableFuture.supplyAsync(() -> {
            try {
                var ini = new Ini();
                if (config != null) {
                    ini.setConfig(config);
                }
                ini.load(getResource(path).getInputStream());
                return ini;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor));
    }

}
