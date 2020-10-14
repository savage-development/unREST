package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.Group;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://www.cyberciti.biz/faq/understanding-etcgroup-file/">The Group File</a>
 */
public class GroupParser implements Parser<Group> {

    private static final short NAME = 0;

    private static final short PASSWORD = 1;

    private static final short GROUP_ID = 2;

    private static final short USERS = 3;

    private static final char DELIMITER = ':';

    private static final char USER_DELIMITER = ',';

    @Override
    public Group parse(String source) {
        var split = split(source);
        return new Group(
                StringUtils.isBlank(split[NAME]) ? null : split[NAME],
                StringUtils.isBlank(split[PASSWORD]) ? null : split[PASSWORD],
                StringUtils.isBlank(split[GROUP_ID]) ? null : Integer.parseInt(split[GROUP_ID]),
                StringUtils.isBlank(split[USERS]) ? Collections.emptySet() : splitUsers(split[USERS])
        );
    }

    private String[] split(String source) {
        return StringUtils.splitPreserveAllTokens(source, DELIMITER);
    }

    private Set<String> splitUsers(String users) {
        String[] split = StringUtils.split(users, USER_DELIMITER);
        return new HashSet<>(Arrays.asList(split));
    }

}
