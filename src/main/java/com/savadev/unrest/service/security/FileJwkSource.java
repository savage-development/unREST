package com.savadev.unrest.service.security;

import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.savadev.unrest.config.properties.SecurityProperties;

import java.util.List;

public class FileJwkSource<C extends SecurityContext> implements JWKSource<C> {

    private final SecurityProperties properties;

    public FileJwkSource(SecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public List<JWK> get(JWKSelector jwkSelector, C context) throws KeySourceException {
        return null;
    }


}
