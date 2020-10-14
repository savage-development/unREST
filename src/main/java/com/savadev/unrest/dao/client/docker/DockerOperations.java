package com.savadev.unrest.dao.client.docker;

import com.savadev.unrest.domain.docker.container.Container;
import com.savadev.unrest.domain.docker.image.Image;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DockerOperations {

    Flux<? extends Container> getAllContainers();

    Flux<? extends Image> getAllImages();

    Mono<Void> stop(String id);

    Mono<Void> start(String id);

    Mono<Void> restart(String id);

    Mono<Void> kill(String id);

    Mono<Void> pause(String id);

    Mono<Void> unpause(String id);

}
