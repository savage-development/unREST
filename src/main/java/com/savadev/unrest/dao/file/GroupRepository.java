package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.Group;
import reactor.core.publisher.Flux;

public interface GroupRepository {

    Flux<Group> getGroups();

}
