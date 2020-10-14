package com.savadev.unrest.dao.file;

import com.savadev.unrest.config.properties.UserProperties;
import com.savadev.unrest.domain.user.User;
import reactor.core.publisher.Flux;

public class UserFileRepository implements UserRepository {

    private final UserProperties properties;

    private final FileResourceLoader loader;

    private final UserParser parser = new UserParser();

    public UserFileRepository(UserProperties properties, FileResourceLoader loader) {
        this.properties = properties;
        this.loader = loader;
    }

    @Override
    public Flux<User> getUsers() {
        return loader.load(properties.getPasswd())
                .map(parser::parse);
    }

}
