package com.savadev.unrest.service.docker;

import com.savadev.unrest.domain.docker.container.Container;

import java.util.function.Predicate;

public class Containers {

    public static Predicate<Container> forId(String id) {
        return container -> container.getId().equals(id);
    }

    public static Predicate<Container> stopped() {
        return container -> exited().test(container) || dead().test(container);
    }

    public static Predicate<Container> started() {
        return container -> exited().test(container);
    }

    public static Predicate<Container> created() {
        return forState("created");
    }

    public static Predicate<Container> restarting() {
        return forState("restarting");
    }

    public static Predicate<Container> running() {
        return forState("running");
    }

    public static Predicate<Container> removing() {
        return forState("removing");
    }

    public static Predicate<Container> paused() {
        return forState("paused");
    }

    public static Predicate<Container> unpaused() {
        return running();
    }

    public static Predicate<Container> exited() {
        return forState("exited");
    }

    public static Predicate<Container> dead() {
        return forState("dead");
    }

    public static Predicate<Container> forState(String state) {
        return container -> container.getState().equalsIgnoreCase(state);
    }

    public static Predicate<Container> isSelf() {
        return Containers::isSelf;
    }

    public static Predicate<Container> isNotSelf() {
        return container -> !isSelf(container);
    }

    public static boolean isSelf(Container container) {
        return "savadev/unrest".equalsIgnoreCase(container.getImage().split(":")[0]);
    }

}
