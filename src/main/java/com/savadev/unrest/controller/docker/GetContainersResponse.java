package com.savadev.unrest.controller.docker;

import com.savadev.unrest.domain.docker.container.Container;

import java.util.Set;

public class GetContainersResponse {

    private final Set<? extends Container> containers;

    public GetContainersResponse(Set<? extends Container> containers) {
        this.containers = containers;
    }

    public Set<? extends Container> getContainers() {
        return containers;
    }

}
