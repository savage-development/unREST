package com.savadev.unrest.controller.config;

import com.savadev.unrest.domain.config.Config;

public class ConfigResponse {

    private final Config config;

    public ConfigResponse(Config config) {
        this.config = config;
    }

    public Config getConfig() {
        return config;
    }
}
