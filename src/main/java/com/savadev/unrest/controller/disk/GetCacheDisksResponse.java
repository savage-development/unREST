package com.savadev.unrest.controller.disk;

import com.savadev.unrest.domain.disk.CacheDisk;

import java.util.Set;

public class GetCacheDisksResponse {

    private final Set<CacheDisk> disks;

    public GetCacheDisksResponse(Set<CacheDisk> disks) {
        this.disks = disks;
    }

    public Set<CacheDisk> getDisks() {
        return disks;
    }

}
