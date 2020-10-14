package com.savadev.unrest.controller.disk;

import com.savadev.unrest.domain.disk.DataDisk;

import java.util.Set;

public class GetDataDisksResponse {

    private final Set<DataDisk> disks;

    public GetDataDisksResponse(Set<DataDisk> disks) {
        this.disks = disks;
    }

    public Set<DataDisk> getDisks() {
        return disks;
    }
}
