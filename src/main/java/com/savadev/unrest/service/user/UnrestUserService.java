package com.savadev.unrest.service.user;

import com.savadev.unrest.dao.file.GroupRepository;
import com.savadev.unrest.dao.file.ShadowRepository;
import com.savadev.unrest.dao.file.UserRepository;
import com.savadev.unrest.domain.user.Group;
import com.savadev.unrest.domain.user.Shadow;
import com.savadev.unrest.domain.user.UnraidUser;
import com.savadev.unrest.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class UnrestUserService implements UserService {

    private final UserRepository userRepository;

    private final GroupRepository groupRepository;

    private final ShadowRepository shadowRepository;

    public UnrestUserService(UserRepository userRepository, GroupRepository groupRepository, ShadowRepository shadowRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.shadowRepository = shadowRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return Mono.zipDelayError(getUser(username), getGroups(username)
                .defaultIfEmpty(Collections.emptySet()), getShadow(username))
                .map(objects -> {
                    var user = objects.getT1();
                    var groups = objects.getT2();
                    var shadow = objects.getT3();
                    return UnraidUser.builder()
                            .username(user.getName())
                            .password(shadow.getPassword() == null ? "" : shadow.getPassword())
                            .accountExpired(isAccountExpired(shadow))
                            .accountLocked(isAccountLocked(shadow))
                            .credentialsExpired(isCredentialsExpired(shadow))
                            .disabled(isDisabled(shadow))
                            .roles(groups.stream()
                                    .map(group -> group.getName().toUpperCase())
                                    .distinct()
                                    .toArray(String[]::new))
                            .build();
                });
    }

    private Mono<User> getUser(String username) {
        return userRepository.getUsers()
                .filter(user -> username.equals(user.getName()))
                .next();
    }

    private Mono<Set<Group>> getGroups(String username) {
        return groupRepository.getGroups()
                .filter(group -> group.getUsers().contains(username))
                .collect(Collectors.toSet());
    }

    private Mono<Shadow> getShadow(String username) {
        return shadowRepository.getShadows()
                .filter(shadow -> username.equals(shadow.getUsername()))
                .next();
    }

    private boolean isAccountExpired(Shadow shadow) {
        return shadow.getExpiration() != null && LocalDate.now().isAfter(shadow.getExpiration());
    }

    private boolean isAccountLocked(Shadow shadow) {
        return shadow.getInactive() != null && LocalDate.now().isAfter(shadow.getInactive());
    }

    private boolean isCredentialsExpired(Shadow shadow) {
        return shadow.getPasswordValidUntil() != null && LocalDate.now().isAfter(shadow.getPasswordValidUntil());
    }

    private boolean isDisabled(Shadow shadow) {
        return isAccountLocked(shadow);
    }

    @Override
    public Flux<Group> getGroups() {
        return groupRepository.getGroups();
    }
}
