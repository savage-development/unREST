package com.savadev.unrest.controller.docker;

import com.savadev.unrest.service.docker.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/docker")
public class DockerController {

    private final DockerService dockerService;

    @Autowired
    public DockerController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("/containers")
    Mono<ResponseEntity<GetContainersResponse>> getContainers() {
        return dockerService.getContainers()
                .collect(Collectors.toSet())
                .map(containers -> ResponseEntity.ok(new GetContainersResponse(containers)));
    }

    @GetMapping("/container/{id}")
    Mono<ResponseEntity<GetContainerResponse>> getContainerById(@PathVariable("id") String id) {
        return dockerService.getContainer(id)
                .map(container -> ResponseEntity.ok(new GetContainerResponse(container)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping("/containers/start")
    Mono<ResponseEntity<Void>> start() {
        return dockerService.start().map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/containers/stop")
    Mono<ResponseEntity<Void>> stop() {
        return dockerService.stop().map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/containers/restart")
    Mono<ResponseEntity<Void>> restart() {
        return dockerService.restart().map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/containers/pause")
    Mono<ResponseEntity<Void>> pause() {
        return dockerService.pause().map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/containers/unpause")
    Mono<ResponseEntity<Void>> unpause() {
        return dockerService.unpause().map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/containers/kill")
    Mono<ResponseEntity<Void>> kill() {
        return dockerService.kill().map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/container/{id}/start")
    Mono<ResponseEntity<Void>> start(@PathVariable("id") String id) {
        return dockerService.start(id).map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/container/{id}/stop")
    Mono<ResponseEntity<Void>> stop(@PathVariable("id") String id) {
        return dockerService.stop(id).map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/container/{id}/restart")
    Mono<ResponseEntity<Void>> restart(@PathVariable("id") String id) {
        return dockerService.restart(id).map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/container/{id}/pause")
    Mono<ResponseEntity<Void>> pause(@PathVariable("id") String id) {
        return dockerService.pause(id).map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/container/{id}/unpause")
    Mono<ResponseEntity<Void>> unpause(@PathVariable("id") String id) {
        return dockerService.unpause(id).map(unused -> ResponseEntity.noContent().build());
    }

    @PostMapping("/container/{id}/kill")
    Mono<ResponseEntity<Void>> kill(@PathVariable("id") String id) {
        return dockerService.kill(id).map(unused -> ResponseEntity.noContent().build());
    }

}
