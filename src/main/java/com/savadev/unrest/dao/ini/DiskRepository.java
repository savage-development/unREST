package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.disk.CacheDisk;
import com.savadev.unrest.domain.disk.DataDisk;
import com.savadev.unrest.domain.disk.FlashDisk;
import com.savadev.unrest.domain.disk.ParityDisk;
import reactor.core.publisher.Flux;

public interface DiskRepository {

    Flux<ParityDisk> getParity();

    Flux<DataDisk> getData();

    Flux<CacheDisk> getCache();

    Flux<FlashDisk> getFlash();

}
