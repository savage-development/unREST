package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.share.Share;
import com.savadev.unrest.utils.IniUtils;
import org.ini4j.Profile;

public abstract class ShareParser<T extends Share> implements Parser<T> {

    protected String getName(Profile.Section section) {
        return IniUtils.getValue(section, "name", false)
                .orElse(null);
    }

    protected String getOriginalName(Profile.Section section) {
        return IniUtils.getValue(section, "nameOrig", false)
                .orElse(null);
    }

    protected String getComment(Profile.Section section) {
        return IniUtils.getValue(section, "comment", false)
                .orElse(null);
    }

    protected String getAllocator(Profile.Section section) {
        return IniUtils.getValue(section, "allocator", false)
                .orElse(null);
    }

    protected String getSplitLevel(Profile.Section section) {
        return IniUtils.getValue(section, "splitLevel", false)
                .orElse(null);
    }

    protected String getFloor(Profile.Section section) {
        return IniUtils.getValue(section, "floor", false)
                .orElse(null);
    }

    protected String getInclude(Profile.Section section) {
        return IniUtils.getValue(section, "include", false)
                .orElse(null);
    }

    protected String getExclude(Profile.Section section) {
        return IniUtils.getValue(section, "exclude", false)
                .orElse(null);
    }

    protected String getUseCache(Profile.Section section) {
        return IniUtils.getValue(section, "useCache", false)
                .orElse(null);
    }

    protected String getCopyOnWrite(Profile.Section section) {
        return IniUtils.getValue(section, "cow", false)
                .orElse(null);
    }

    protected Long getFree(Profile.Section section) {
        return IniUtils.getValue(section, "free", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected Long getSize(Profile.Section section) {
        return IniUtils.getValue(section, "size", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected Boolean isEncrypted(Profile.Section section) {
        return IniUtils.getValue(section, "luksStatus")
                .map(Integer::parseInt)
                .map(integer -> integer == 1)
                .orElse(null);
    }

}
