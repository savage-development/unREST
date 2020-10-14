package com.savadev.unrest.domain.disk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.savadev.unrest.domain.Device;

public interface Disk extends Device {

    Integer getIdx();

    String getName();

    String getDevice();

    Long getSize();

    Status getStatus();

    Boolean isRotational();

    String getFormat();

    Integer getTemperature();

    Long getReads();

    Long getWrites();

    Long getErrors();

    Long getSpinDownDelay();

    String getSpinUpGroup();

    @JsonIgnore
    default boolean isParity() {
        return this instanceof ParityDisk;
    }

    @JsonIgnore
    default boolean isData() {
        return this instanceof DataDisk;
    }

    @JsonIgnore
    default boolean isCache() {
        return this instanceof CacheDisk;
    }

    @JsonIgnore
    default boolean isFlash() {
        return this instanceof FlashDisk;
    }

    enum Status {
        DISK_NP,
        DISK_NP_DSBL,
        DISK_NP_MISSING,
        DISK_OK,
        DISK_DSBL,
        DISK_INVALID,
        DISK_DSBL_NEW,
        DISK_NEW,
        DISK_WRONG
    }

}
