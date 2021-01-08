package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.User;
import reactor.core.publisher.Flux;

public class UserFileRepository implements UserRepository {

    private final FileResourceLoader loader;

    private final UserParser parser = new UserParser();

    public UserFileRepository(FileResourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public Flux<User> getUsers() {
        return loader.load().map(parser::parse);
    }

}
