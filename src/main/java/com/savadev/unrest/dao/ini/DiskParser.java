package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.utils.IniUtils;
import org.ini4j.Profile;

abstract class DiskParser<T extends Disk> implements Parser<T> {

    protected Integer getIdx(Profile.Section section) {
        return IniUtils.getValue(section, "idx", false)
                .map(Integer::parseInt)
                .orElse(null);
    }

    protected String getName(Profile.Section section) {
        return IniUtils.getValue(section, "name", false)
                .orElse(null);
    }

    protected String getDevice(Profile.Section section) {
        return IniUtils.getValue(section, "device", false)
                .orElse(null);
    }

    protected String getId(Profile.Section section) {
        return IniUtils.getValue(section, "id", false)
                .orElse(null);
    }

    protected Long getSize(Profile.Section section) {
        return IniUtils.getValue(section, "size", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected Disk.Status getStatus(Profile.Section section) {
        return IniUtils.getValue(section, "status", false)
                .map(Disk.Status::valueOf)
                .orElse(null);
    }

    protected Boolean isRotational(Profile.Section section) {
        return IniUtils.getValue(section, "rotational", false)
                .map(Integer::parseInt)
                .map(rotational -> rotational == 1)
                .orElse(null);
    }

    protected String getFormat(Profile.Section section) {
        return IniUtils.getValue(section, "format", false)
                .orElse(null);
    }

    protected Integer getTemperature(Profile.Section section) {
        return IniUtils.getValue(section, "temp", false)
                .filter(s -> {
                    try {
                        Integer.parseInt(s);
                        return true;
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                })
                .map(Integer::parseInt)
                .orElse(null);
    }

    protected Long getReads(Profile.Section section) {
        return IniUtils.getValue(section, "numReads", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected Long getWrites(Profile.Section section) {
        return IniUtils.getValue(section, "numWrites", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected Long getErrors(Profile.Section section) {
        return IniUtils.getValue(section, "numErrors", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected Long getSpinDownDelay(Profile.Section section) {
        return IniUtils.getValue(section, "spindownDelay", false)
                .map(Long::parseLong)
                .orElse(null);
    }

    protected String getSpinUpGroup(Profile.Section section) {
        return IniUtils.getValue(section, "spinupGroup", false)
                .orElse(null);
    }

}
