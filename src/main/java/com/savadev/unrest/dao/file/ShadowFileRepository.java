package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.Shadow;
import reactor.core.publisher.Flux;

public class ShadowFileRepository implements ShadowRepository {

    private final FileResourceLoader loader;

    private final ShadowParser parser = new ShadowParser();

    public ShadowFileRepository(FileResourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public Flux<Shadow> getShadows() {
        return loader.load().map(parser::parse);
    }

}
