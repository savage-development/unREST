package com.savadev.unrest.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Version {

    public static final String V683_VALUE = "6.8.3";

    public static final Version V683 = new Version(V683_VALUE, 683);

    public static final Map<String, Version> VERSIONS = new HashMap<>();

    static {
        VERSIONS.put(V683_VALUE, V683);
    }

    private final String version;

    private final int code;

    private Version(String version, int code) {
        this.version = version;
        this.code = code;
    }

    public static Optional<Version> parse(String version) {
        return Optional.ofNullable(VERSIONS.get(version));
    }

    public static Optional<Version> parse(int code) {
        return VERSIONS.values().stream()
                .filter(version -> version.code == code)
                .findFirst();
    }

    public String getVersion() {
        return version;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return version;
    }
}
