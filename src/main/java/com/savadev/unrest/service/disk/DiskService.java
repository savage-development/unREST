package com.savadev.unrest.service.disk;

import com.savadev.unrest.domain.disk.CacheDisk;
import com.savadev.unrest.domain.disk.DataDisk;
import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.domain.disk.FlashDisk;
import com.savadev.unrest.domain.disk.ParityDisk;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DiskService {

    Flux<Disk> getDisks();

    Mono<Disk> getDiskById(int idx);

    Flux<ParityDisk> getParityDisks();

    Flux<DataDisk> getDataDisks();

    Flux<CacheDisk> getCacheDisks();

    Flux<FlashDisk> getFlashDisks();

    Mono<Void> spinUp();

    Mono<Void> spinUp(int idx);

    Mono<Void> spinUpParity();

    Mono<Void> spinUpCache();

    Mono<Void> spinUpData();

    Mono<Void> spinDown();

    Mono<Void> spinDown(int idx);

    Mono<Void> spinDownParity();

    Mono<Void> spinDownCache();

    Mono<Void> spinDownData();

}
