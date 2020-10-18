package com.savadev.unrest.controller.security;

import com.nimbusds.jwt.JWT;

public class GenerateJwtResponse {

    private final JWT token;

    public GenerateJwtResponse(JWT token) {
        this.token = token;
    }

    public String getToken() {
        return token.serialize();
    }

}
