package com.savadev.unrest.service.docker;

import com.savadev.unrest.dao.client.docker.DockerOperations;
import com.savadev.unrest.domain.docker.container.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
                .next();
    }

    @Override
    public Mono<Void> start() {
        return getContainers()
                .filter(Containers.stopped())
                .parallel()
                .flatMap(container -> start(container.getId()))
                .then();
    }

    @Override
    public Mono<Void> start(String id) {
        logger.info("Starting container: {}", id);
        return operations.start(id);
    }

    @Override
    public Mono<Void> stop() {
        return getContainers()
                .filter(Containers.started())
                .parallel()
                .flatMap(container -> stop(container.getId()))
                .then();
    }

    @Override
    public Mono<Void> stop(String id) {
        logger.info("Stopping container: {}", id);
        return operations.stop(id);
    }

    @Override
    public Mono<Void> restart() {
        return getContainers()
                .parallel()
                .flatMap(container -> restart(container.getId()))
                .then();
    }

    @Override
    public Mono<Void> restart(String id) {
        logger.info("Restarting container: {}", id);
        return operations.restart(id);
    }

    @Override
    public Mono<Void> kill() {
        return getContainers()
                .parallel()
                .flatMap(container -> restart(container.getId()))
                .then();
    }

    @Override
    public Mono<Void> kill(String id) {
        logger.info("Killing container: {}", id);
        return operations.kill(id);
    }

    @Override
    public Mono<Void> pause() {
        return getContainers()
                .filter(Containers.unpaused())
                .parallel()
                .flatMap(container -> pause(container.getId()))
                .then();
    }

    @Override
    public Mono<Void> pause(String id) {
        logger.info("Pausing container: {}", id);
        return operations.pause(id);
    }

    @Override
    public Mono<Void> unpause() {
        return getContainers()
                .filter(Containers.paused())
                .parallel()
                .flatMap(container -> unpause(container.getId()))
                .then();
    }

    @Override
    public Mono<Void> unpause(String id) {
        logger.info("Unpausing container: {}", id);
        return operations.unpause(id);
    }

}
