package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.disk.DataDisk;
import org.ini4j.Profile;

public class DataDiskParser extends DiskParser<DataDisk> {

    @Override
    public DataDisk parse(Profile.Section section) {
        return DataDisk.builder()
                .withIdx(getIdx(section))
                .withName(getName(section))
                .withDevice(getDevice(section))
                .withId(getId(section))
                .withSize(getSize(section))
                .withStatus(getStatus(section))
                .isRotational(isRotational(section))
                .withFormat(getFormat(section))
                .withTemperature(getTemperature(section))
                .withReads(getReads(section))
                .withWrites(getWrites(section))
                .withErrors(getErrors(section))
                .withSpinDownDelay(getSpinDownDelay(section))
                .withSpinUpGroup(getSpinUpGroup(section))
                .build();
    }

}
