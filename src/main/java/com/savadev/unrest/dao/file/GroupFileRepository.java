package com.savadev.unrest.dao.file;

import com.savadev.unrest.config.properties.UserProperties;
import com.savadev.unrest.domain.user.Group;
import reactor.core.publisher.Flux;

public class GroupFileRepository implements GroupRepository {

    private final UserProperties properties;

    private final FileResourceLoader loader;

    private final GroupParser parser = new GroupParser();

    public GroupFileRepository(UserProperties properties, FileResourceLoader loader) {
        this.properties = properties;
        this.loader = loader;
    }

    @Override
    public Flux<Group> getGroups() {
        return loader.load(properties.getGroup())
                .map(parser::parse);
    }

}
