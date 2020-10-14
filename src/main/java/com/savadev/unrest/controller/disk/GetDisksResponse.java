package com.savadev.unrest.controller.disk;

import com.savadev.unrest.domain.disk.CacheDisk;
import com.savadev.unrest.domain.disk.DataDisk;
import com.savadev.unrest.domain.disk.FlashDisk;
import com.savadev.unrest.domain.disk.ParityDisk;

import java.util.Set;

public class GetDisksResponse {

    private final Set<CacheDisk> cache;

    private final Set<DataDisk> data;

    private final Set<FlashDisk> flash;

    private final Set<ParityDisk> parity;

    public GetDisksResponse(Set<CacheDisk> cache, Set<DataDisk> data, Set<FlashDisk> flash, Set<ParityDisk> parity) {
        this.cache = cache;
        this.data = data;
        this.flash = flash;
        this.parity = parity;
    }

    public Set<CacheDisk> getCache() {
        return cache;
    }

    public Set<DataDisk> getData() {
        return data;
    }

    public Set<FlashDisk> getFlash() {
        return flash;
    }

    public Set<ParityDisk> getParity() {
        return parity;
    }
}
