package com.savadev.unrest.config.properties;

public class SecurityProperties {

    private String path;

    private String jwk;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getJwk() {
        return jwk;
    }

    public void setJwk(String jwk) {
        this.jwk = jwk;
    }
}
