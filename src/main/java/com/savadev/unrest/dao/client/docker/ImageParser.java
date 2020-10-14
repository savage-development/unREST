package com.savadev.unrest.dao.client.docker;

import com.savadev.unrest.domain.docker.image.Image;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class ImageParser implements Parser<Flux<? extends Image>> {

    @Override
    public Flux<? extends Image> parse(String version, WebClient.ResponseSpec response) {
        return null;
    }

}
