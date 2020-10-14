package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.disk.ParityDisk;
import org.ini4j.Profile;

public class ParityDiskParser extends DiskParser<ParityDisk> {

    @Override
    public ParityDisk parse(Profile.Section section) {
        return ParityDisk.builder()
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
