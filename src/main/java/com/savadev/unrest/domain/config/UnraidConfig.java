package com.savadev.unrest.domain.config;

import com.savadev.unrest.domain.Version;

public class UnraidConfig implements Config {

    private final Version version;

    private final String timezone;

    private final String description;

    public UnraidConfig(Version version, String timezone, String description) {
        this.version = version;
        this.timezone = timezone;
        this.description = description;
    }

    @Override
    public Version getVersion() {
        return version;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
