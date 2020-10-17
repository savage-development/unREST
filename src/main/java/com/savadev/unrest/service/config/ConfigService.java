package com.savadev.unrest.service.config;

import com.savadev.unrest.domain.config.Config;
import reactor.core.publisher.Mono;

public interface ConfigService {

    Mono<Config> getConfiguration();

}
