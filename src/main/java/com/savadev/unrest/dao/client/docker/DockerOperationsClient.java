package com.savadev.unrest.dao.client.docker;

import com.savadev.unrest.config.properties.DockerOperationsProperties;
import com.savadev.unrest.domain.docker.container.Container;
import com.savadev.unrest.domain.docker.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DockerOperationsClient implements DockerOperations {

    private static final Logger logger = LoggerFactory.getLogger(DockerOperationsClient.class);

    private final DockerOperationsProperties properties;

    private final WebClient client;

    public DockerOperationsClient(DockerOperationsProperties properties, WebClient client) {
        this.properties = properties;
        this.client = client;
    }

    @Override
    public Flux<? extends Container> getAllContainers() {
        return new ContainerParser()
                .parse(properties.getVersion(), client.get()
                        .uri(properties.getAllContainers())
                        .retrieve())
                .map(container -> {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Fetched container {}", container);
                    }
                    return container;
                });
    }

    @Override
    public Flux<? extends Image> getAllImages() {
        return null;
    }

    @Override
    public Mono<Void> stop(String id) {
        return client.post()
                .uri(properties.getStopContainer().expand(id))
                // Work around for empty body bug with docker.
                .body(BodyInserters.fromValue("empty"))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Error stopping container")))
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> start(String id) {
        return client.post()
                .uri(properties.getStartContainer().expand(id))
                // Work around for empty body bug with docker.
                .body(BodyInserters.fromValue("empty"))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Error starting container")))
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> restart(String id) {
        return client.post()
                .uri(properties.getRestartContainer().expand(id))
                .body(BodyInserters.fromValue("empty"))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Error restarting container")))
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> kill(String id) {
        return client.post()
                .uri(properties.getKillContainer().expand(id))
                .body(BodyInserters.fromValue("empty"))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Error killing container")))
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> pause(String id) {
        return client.post()
                .uri(properties.getPauseContainer().expand(id))
                .body(BodyInserters.fromValue("empty"))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Error pausing container")))
                .bodyToMono(Void.class);
    }

    @Override
    public Mono<Void> unpause(String id) {
        return client.post()
                .uri(properties.getUnpauseContainer().expand(id))
                .body(BodyInserters.fromValue("empty"))
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new RuntimeException("Error unpausing container")))
                .bodyToMono(Void.class);
    }

}
