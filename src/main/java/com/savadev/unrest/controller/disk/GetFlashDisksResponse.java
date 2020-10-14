package com.savadev.unrest.controller.disk;

import com.savadev.unrest.domain.disk.FlashDisk;

import java.util.Set;

public class GetFlashDisksResponse {

    private final Set<FlashDisk> disks;

    public GetFlashDisksResponse(Set<FlashDisk> disks) {
        this.disks = disks;
    }

    public Set<FlashDisk> getDisks() {
        return disks;
    }
}
