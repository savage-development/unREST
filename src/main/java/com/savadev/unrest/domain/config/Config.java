package com.savadev.unrest.domain.config;

import com.savadev.unrest.domain.Version;

public interface Config {

    Version getVersion();

    String getTimezone();

    String getDescription();

}
