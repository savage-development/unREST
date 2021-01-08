package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.Group;
import reactor.core.publisher.Flux;

public class GroupFileRepository implements GroupRepository {

    private final FileResourceLoader loader;

    private final GroupParser parser = new GroupParser();

    public GroupFileRepository(FileResourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public Flux<Group> getGroups() {
        return loader.load().map(parser::parse);
    }

}
