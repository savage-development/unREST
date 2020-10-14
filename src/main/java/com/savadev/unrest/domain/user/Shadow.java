package com.savadev.unrest.domain.user;

import java.time.LocalDate;

/**
 * Represents a shadow entry from the /etc/shadow file.
 */
public class Shadow {

    private final String username;

    private final String password;

    private final LocalDate lastPasswordChange;

    private final LocalDate nextPasswordChange;

    private final LocalDate passwordValidUntil;

    private final LocalDate passwordExpiresWarning;

    private final LocalDate inactive;

    private final LocalDate expiration;

    public Shadow(String username, String password, LocalDate lastPasswordChange, LocalDate nextPasswordChange,
                  LocalDate passwordValidUntil, LocalDate passwordExpiresWarning, LocalDate inactive, LocalDate expiration) {
        this.username = username;
        this.password = password;
        this.lastPasswordChange = lastPasswordChange;
        this.nextPasswordChange = nextPasswordChange;
        this.passwordValidUntil = passwordValidUntil;
        this.passwordExpiresWarning = passwordExpiresWarning;
        this.inactive = inactive;
        this.expiration = expiration;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getLastPasswordChange() {
        return lastPasswordChange;
    }

    public LocalDate getNextPasswordChange() {
        return nextPasswordChange;
    }

    public LocalDate getPasswordValidUntil() {
        return passwordValidUntil;
    }

    public LocalDate getPasswordExpiresWarning() {
        return passwordExpiresWarning;
    }

    public LocalDate getInactive() {
        return inactive;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

}
