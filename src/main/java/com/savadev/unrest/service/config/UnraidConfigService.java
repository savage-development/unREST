package com.savadev.unrest.service.config;

import com.savadev.unrest.dao.ini.VarRepository;
import com.savadev.unrest.domain.config.Config;
import com.savadev.unrest.domain.config.UnraidConfig;
import reactor.core.publisher.Mono;

public class UnraidConfigService implements ConfigService {

    private final VarRepository repository;

    public UnraidConfigService(VarRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Config> getConfiguration() {
        return Mono.zip(repository.getVersion(), repository.getTimezone(), repository.getComment())
                .map(objects -> new UnraidConfig(objects.getT1(), objects.getT2(), objects.getT3()));
    }

}
