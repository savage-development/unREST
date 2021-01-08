package com.savadev.unrest.config.properties;

import java.net.URI;

public class SecurityProperties {

    private URI jwk;

    public URI getJwk() {
        return jwk;
    }

    public void setJwk(URI jwk) {
        this.jwk = jwk;
    }
}
