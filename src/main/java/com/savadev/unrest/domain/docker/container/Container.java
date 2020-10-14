package com.savadev.unrest.domain.docker.container;

import java.time.Instant;
import java.util.Set;

public interface Container {

    String getId();

    Set<String> getNames();

    String getImage();

    String getImageId();

    String getCommand();

    Instant getCreated();

    String getState();

    String getStatus();

}
