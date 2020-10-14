package com.savadev.unrest.dao.file;

import com.savadev.unrest.dao.ResourceLoader;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.BaseStream;

public class FileResourceLoader implements ResourceLoader<String> {

    @Override
    public Flux<String> load(String resource) {
        return Flux.using(() -> Files.lines(Paths.get(new URI(resource))), Flux::fromStream, BaseStream::close);
    }

}
