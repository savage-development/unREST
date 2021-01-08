package com.savadev.unrest.service.docker;

import com.savadev.unrest.dao.client.docker.DockerOperations;
import com.savadev.unrest.domain.docker.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UnrestDockerService implements DockerService {

    private static final Logger logger = LoggerFactory.getLogger(UnrestDockerService.class);

    private final DockerOperations operations;

    public UnrestDockerService(DockerOperations operations) {
        this.operations = operations;
    }

    @Override
    public Flux<? extends Container> getContainers() {
        return operations.getAllContainers();
    }

    @Override
    public Mono<? extends Container> getContainer(String id) {
        return getContainers()
                .filter(Containers.forId(id))
                .switchIfEmpty(Mono.error(new DockerServiceException(HttpStatus.NOT_FOUND, String.format("Container with ID: %s not found.", id))))
                .next();
    }

    @Override
    public Mono<Void> start() {
        return getContainers()
                .filter(Containers.stopped())
                .parallel()
                .flatMap(this::start)
                .then();
    }

    @Override
    public Mono<Void> start(String id) {
        logger.info("Starting container: {}", id);
        return getContainer(id).flatMap(this::start);
    }

    @Override
    public Mono<Void> stop() {
        return getContainers()
                .filter(Containers.isNotSelf())
                .filter(Containers.started())
                .parallel()
                .flatMap(this::stop)
                .then();
    }

    @Override
    public Mono<Void> stop(String id) {
        logger.info("Stopping container: {}", id);
        return getContainer(id).flatMap(this::stop);
    }

    @Override
    public Mono<Void> restart() {
        return getContainers()
                .parallel()
                .flatMap(this::restart)
                .then();
    }

    @Override
    public Mono<Void> restart(String id) {
        logger.info("Restarting container: {}", id);
        return getContainer(id).flatMap(this::restart);
    }

    @Override
    public Mono<Void> kill() {
        return getContainers()
                .filter(Containers.isNotSelf())
                .parallel()
                .flatMap(this::kill)
                .then();
    }

    @Override
    public Mono<Void> kill(String id) {
        logger.info("Killing container: {}", id);
        return getContainer(id).flatMap(this::kill);
    }

    @Override
    public Mono<Void> pause() {
        return getContainers()
                .filter(Containers.isNotSelf())
                .filter(Containers.unpaused())
                .parallel()
                .flatMap(this::pause)
                .then();
    }

    @Override
    public Mono<Void> pause(String id) {
        logger.info("Pausing container: {}", id);
        return getContainer(id).flatMap(this::pause);
    }

    @Override
    public Mono<Void> unpause() {
        return getContainers()
                .filter(Containers.paused())
                .parallel()
                .flatMap(this::unpause)
                .then();
    }

    @Override
    public Mono<Void> unpause(String id) {
        logger.info("Unpausing container: {}", id);
        return getContainer(id).flatMap(this::unpause);
    }

    private Mono<Void> start(Container container) {
        return operations.start(container.getId());
    }

    private Mono<Void> stop(Container container) {
        if (Containers.isSelf(container)) {
            return Mono.error(new DockerServiceException(HttpStatus.BAD_REQUEST, "Attempt to stop unREST API container blocked."));
        }
        return operations.stop(container.getId());
    }

    private Mono<Void> restart(Container container) {
        return operations.restart(container.getId());
    }

    private Mono<Void> kill(Container container) {
        if (Containers.isSelf(container)) {
            return Mono.error(new DockerServiceException(HttpStatus.BAD_REQUEST, "Attempt to kill unREST API container blocked."));
        }
        return operations.kill(container.getId());
    }

    private Mono<Void> pause(Container container) {
        if (Containers.isSelf(container)) {
            return Mono.error(new DockerServiceException(HttpStatus.BAD_REQUEST, "Attempt to pause unREST API container blocked."));
        }
        return operations.pause(container.getId());
    }

    private Mono<Void> unpause(Container container) {
        return operations.unpause(container.getId());
    }

}
