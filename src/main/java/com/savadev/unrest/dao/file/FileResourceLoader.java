package com.savadev.unrest.dao.file;

import com.savadev.unrest.dao.AbstractResourceLoader;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.BaseStream;

public class FileResourceLoader extends AbstractResourceLoader<String> {

    private final URI resource;

    public FileResourceLoader(URI resource) {
        this.resource = resource;
    }

    @Override
    public Flux<String> load() {
        return Flux.using(() -> Files.lines(Paths.get(getResource(resource).getURI())), Flux::fromStream, BaseStream::close);
    }

}
