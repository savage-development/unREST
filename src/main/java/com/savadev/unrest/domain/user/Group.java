package com.savadev.unrest.domain.user;

import java.util.Set;

/**
 * Represents a group entry from the /etc/group file.
 */
public class Group {

    private final String name;

    private final String password;

    private final Integer id;

    private final Set<String> users;

    public Group(String name, String password, Integer id, Set<String> users) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public Set<String> getUsers() {
        return users;
    }
}
