package com.savadev.unrest.dao.client.docker;

import com.savadev.unrest.domain.docker.container.Container;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class ContainerParser implements Parser<Flux<? extends Container>> {

    @Override
    public Flux<? extends Container> parse(String version, WebClient.ResponseSpec response) {
        return switch (version) {
            case "1.40" -> response.bodyToFlux(ContainerResponse.class);
            default -> throw new RuntimeException();
        };
    }

}
