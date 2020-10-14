package com.savadev.unrest.service.user;

import com.savadev.unrest.domain.user.Group;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import reactor.core.publisher.Flux;

public interface UserService extends ReactiveUserDetailsService {

    Flux<Group> getGroups();

}
