package com.savadev.unrest.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.net.URI;

public abstract class AbstractResourceLoader<T> implements ResourceLoader<T> {

    protected Resource getResource(URI resource) {
        return switch (resource.getScheme()) {
            case "classpath" -> new ClassPathResource(resource.getPath());
            case "file" -> new FileSystemResource(resource.getPath());
            default -> throw new UnsupportedOperationException("Unsupported scheme");
        };
    }

}
