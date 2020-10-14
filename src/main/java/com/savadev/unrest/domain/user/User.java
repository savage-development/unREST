package com.savadev.unrest.domain.user;

/**
 * Represents a user entry from the /etc/passwd file.
 */
public class User {

    private final String name;

    private final String password;

    private final Integer userId;

    private final Integer groupId;

    private final String comment;

    private final String home;

    private final String shell;

    public User(String name, String password, Integer userId, Integer groupId, String comment, String home, String shell) {
        this.name = name;
        this.password = password;
        this.userId = userId;
        this.groupId = groupId;
        this.comment = comment;
        this.home = home;
        this.shell = shell;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public String getComment() {
        return comment;
    }

    public String getHome() {
        return home;
    }

    public String getShell() {
        return shell;
    }

}
