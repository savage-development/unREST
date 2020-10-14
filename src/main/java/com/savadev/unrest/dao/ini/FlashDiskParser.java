package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.disk.FlashDisk;
import org.ini4j.Profile;

public class FlashDiskParser extends DiskParser<FlashDisk> {
    @Override
    public FlashDisk parse(Profile.Section section) {
        return FlashDisk.builder()
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
