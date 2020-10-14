package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.User;
import reactor.core.publisher.Flux;

public interface UserRepository {

    Flux<User> getUsers();

}
