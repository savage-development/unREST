package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.User;
import org.apache.commons.lang3.StringUtils;

/**
 * <a href="https://www.cyberciti.biz/faq/understanding-etcpasswd-file-format/">The Passwd File</a>
 */
public class UserParser implements Parser<User> {

    private static final short USERNAME = 0;

    private static final short PASSWORD = 1;

    private static final short USER_ID = 2;

    private static final short GROUP_ID = 3;

    private static final short COMMENT = 4;

    private static final short HOME = 5;

    private static final short SHELL = 6;

    private static final char DELIMITER = ':';

    @Override
    public User parse(String source) {
        var split = split(source);
        return new User(
                StringUtils.isBlank(split[USERNAME]) ? null : split[USERNAME],
                StringUtils.isBlank(split[PASSWORD]) ? null : split[PASSWORD],
                StringUtils.isBlank(split[USER_ID]) ? null : Integer.parseInt(split[USER_ID]),
                StringUtils.isBlank(split[GROUP_ID]) ? null : Integer.parseInt(split[GROUP_ID]),
                StringUtils.isBlank(split[COMMENT]) ? null : split[COMMENT],
                StringUtils.isBlank(split[HOME]) ? null : split[HOME],
                StringUtils.isBlank(split[SHELL]) ? null : split[SHELL]
        );
    }

    private String[] split(String source) {
        return StringUtils.splitPreserveAllTokens(source, DELIMITER);
    }

}
