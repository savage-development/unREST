package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.Shadow;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * <a href="https://www.cyberciti.biz/faq/understanding-etcshadow-file/">The Shadow File</a>
 */
public class ShadowParser implements Parser<Shadow> {

    private static final short USERNAME = 0;

    private static final short PASSWORD = 1;

    private static final short LAST_PASSWORD_CHANGE = 2;

    private static final short MINIMUM = 3;

    private static final short MAXIMUM = 4;

    private static final short WARN = 5;

    private static final short INACTIVE = 6;

    private static final short EXPIRE = 7;

    private static final char DELIMITER = ':';

    @Override
    public Shadow parse(String source) {
        var split = split(source);
        return new Shadow(
                getUsername(split[USERNAME]),
                getPassword(split[PASSWORD]),
                getLastPasswordChange(split[LAST_PASSWORD_CHANGE]),
                getNextPasswordChange(split[MINIMUM]),
                getPasswordExpires(split[MAXIMUM]),
                getPasswordExpiresWarning(split[MAXIMUM], split[WARN]),
                getAccountInactive(split[MAXIMUM], split[INACTIVE]),
                getAccountExpiration(split[EXPIRE])
        );
    }

    private String[] split(String source) {
        return StringUtils.splitPreserveAllTokens(source, DELIMITER);
    }

    private String getUsername(String source) {
        return StringUtils.isBlank(source) ? null : source;
    }

    private String getPassword(String source) {
        return StringUtils.isBlank(source) ? null : source;
    }

    private LocalDate getLastPasswordChange(String source) {
        return StringUtils.isBlank(source) ? null : epoch().plus(Integer.parseInt(source), ChronoUnit.DAYS);
    }

    private LocalDate getNextPasswordChange(String source) {
        return StringUtils.isBlank(source) ? null : now().plus(Integer.parseInt(source), ChronoUnit.DAYS);
    }

    private LocalDate getPasswordExpires(String source) {
        return StringUtils.isBlank(source) ? null : now().plus(Integer.parseInt(source), ChronoUnit.DAYS);
    }

    private LocalDate getPasswordExpiresWarning(String expiration, String source) {
        var expires = getPasswordExpires(expiration);
        if (expires == null) {
            return null;
        }
        return StringUtils.isBlank(source) ? null : expires.minus(Integer.parseInt(source), ChronoUnit.DAYS);
    }

    private LocalDate getAccountInactive(String expiration, String source) {
        var expires = getPasswordExpires(expiration);
        if (expires == null) {
            return null;
        }
        return StringUtils.isBlank(source) ? null : expires.plus(Integer.parseInt(source), ChronoUnit.DAYS);
    }

    private LocalDate getAccountExpiration(String source) {
        return StringUtils.isBlank(source) ? null : epoch().plus(Integer.parseInt(source), ChronoUnit.DAYS);
    }

    private LocalDate epoch() {
        return LocalDate.EPOCH;
    }

    private LocalDate now() {
        return LocalDate.now();
    }

}
