package com.savadev.unrest.dao.file;

import com.savadev.unrest.config.properties.UserProperties;
import com.savadev.unrest.domain.user.Shadow;
import reactor.core.publisher.Flux;

public class ShadowFileRepository implements ShadowRepository {

    private final UserProperties properties;

    private final FileResourceLoader loader;

    private final ShadowParser parser = new ShadowParser();

    public ShadowFileRepository(UserProperties properties, FileResourceLoader loader) {
        this.properties = properties;
        this.loader = loader;
    }

    @Override
    public Flux<Shadow> getShadows() {
        return loader.load(properties.getShadow())
                .map(parser::parse);
    }

}
