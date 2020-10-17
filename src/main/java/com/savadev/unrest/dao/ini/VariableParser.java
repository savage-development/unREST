package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.Version;
import com.savadev.unrest.domain.var.UnraidVariables;
import com.savadev.unrest.domain.var.Variables;
import com.savadev.unrest.utils.IniUtils;
import org.ini4j.Profile;

public class VariableParser implements Parser<Variables> {

    @Override
    public Variables parse(Profile.Section section) {
        return UnraidVariables.builder()
                .withVersion(getVersion(section))
                .withTimezone(getTimezone(section))
                .withComment(getComment(section))
                .withCsrfToken(getCsrfToken(section))
                .withMdState(getMdState(section))
                .build();
    }

    protected Version getVersion(Profile.Section section) {
        return IniUtils.getValue(section, "version", false)
                .flatMap(Version::parse)
                .orElse(null);
    }

    protected String getTimezone(Profile.Section section) {
        return IniUtils.getValue(section, "timeZone", false)
                .orElse(null);
    }

    protected String getComment(Profile.Section section) {
        return IniUtils.getValue(section, "COMMENT", false)
                .orElse(null);
    }

    protected String getCsrfToken(Profile.Section section) {
        return IniUtils.getValue(section, "csrf_token", false)
                .orElse(null);
    }

    protected String getMdState(Profile.Section section) {
        return IniUtils.getValue(section, "mdState", false)
                .orElse(null);
    }

}
