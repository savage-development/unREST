package com.savadev.unrest.controller.docker;

import com.savadev.unrest.domain.docker.container.Container;

public class GetContainerResponse {

    private final Container container;

    public GetContainerResponse(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }
}
