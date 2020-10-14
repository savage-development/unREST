package com.savadev.unrest.controller.disk;

import com.savadev.unrest.domain.disk.ParityDisk;

import java.util.Set;

public class GetParityDisksResponse {

    private final Set<ParityDisk> disks;

    public GetParityDisksResponse(Set<ParityDisk> disks) {
        this.disks = disks;
    }

    public Set<ParityDisk> getDisks() {
        return disks;
    }
}
