package com.savadev.unrest.service.security;

import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CryptPasswordEncoder implements PasswordEncoder {

    private static final String DELIMITER = "$";

    @Override
    public String encode(CharSequence rawPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            if (encodedPassword == null || encodedPassword.isEmpty()) {
                return true;
            }
            var hash = Crypt.crypt(rawPassword.toString(), getSalt(encodedPassword));
            return StringUtils.equals(hash, encodedPassword);
        } catch (Exception ex) {
            return false;
        }
    }

    private String getSalt(String encoded) {
        return StringUtils.substringBeforeLast(encoded, DELIMITER);
    }

}
