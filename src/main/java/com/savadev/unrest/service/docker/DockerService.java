package com.savadev.unrest.service.docker;

import com.savadev.unrest.domain.docker.container.Container;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DockerService {

    Flux<? extends Container> getContainers();

    Mono<? extends Container> getContainer(String id);

    Mono<Void> start();

    Mono<Void> start(String id);

    Mono<Void> stop();

    Mono<Void> stop(String id);

    Mono<Void> restart();

    Mono<Void> restart(String id);

    Mono<Void> kill();

    Mono<Void> kill(String id);

    Mono<Void> pause();

    Mono<Void> pause(String id);

    Mono<Void> unpause();

    Mono<Void> unpause(String id);

}
