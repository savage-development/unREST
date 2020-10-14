package com.savadev.unrest.service.disk;

import com.savadev.unrest.dao.client.disk.DiskOperations;
import com.savadev.unrest.dao.ini.DiskRepository;
import com.savadev.unrest.domain.disk.CacheDisk;
import com.savadev.unrest.domain.disk.DataDisk;
import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.domain.disk.FlashDisk;
import com.savadev.unrest.domain.disk.ParityDisk;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UnrestDiskService implements DiskService {

    private final DiskRepository repository;

    private final DiskOperations operations;

    public UnrestDiskService(DiskRepository repository, DiskOperations operations) {
        this.repository = repository;
        this.operations = operations;
    }

    @Override
    public Flux<Disk> getDisks() {
        return Flux.concat(
                getParityDisks(),
                getDataDisks(),
                getCacheDisks(),
                getFlashDisks()
        );
    }

    @Override
    public Flux<ParityDisk> getParityDisks() {
        return repository.getParity();
    }

    @Override
    public Flux<DataDisk> getDataDisks() {
        return repository.getData();
    }

    @Override
    public Flux<CacheDisk> getCacheDisks() {
        return repository.getCache();
    }

    @Override
    public Flux<FlashDisk> getFlashDisks() {
        return repository.getFlash();
    }

    @Override
    public Mono<Void> spinUp(int idx) {
        return getDisks()
                .filter(Disks.byIdx(idx))
                .filter(Disks.isNotFlash())
                .next()
                .flatMap(operations::spinUp);
    }

    @Override
    public Mono<Void> spinUpParity() {
        return getParityDisks()
                .parallel()
                .flatMap(parityDisk -> spinUp(parityDisk.getIdx()))
                .then();
    }

    @Override
    public Mono<Void> spinUpCache() {
        return getCacheDisks()
                .parallel()
                .flatMap(cacheDisk -> spinUp(cacheDisk.getIdx()))
                .then();
    }

    @Override
    public Mono<Void> spinUpData() {
        return getDataDisks()
                .parallel()
                .flatMap(dataDisk -> spinUp(dataDisk.getIdx()))
                .then();
    }

    @Override
    public Mono<Void> spinDown() {
        return operations.spinDownAll();
    }

    @Override
    public Mono<Void> spinDown(int idx) {
        return getDisks()
                .filter(Disks.byIdx(idx))
                .filter(Disks.isNotFlash())
                .next()
                .flatMap(operations::spinDown);
    }

    @Override
    public Mono<Void> spinDownParity() {
        return getParityDisks()
                .parallel()
                .flatMap(parityDisk -> spinDown(parityDisk.getIdx()))
                .then();
    }

    @Override
    public Mono<Void> spinDownCache() {
        return getCacheDisks()
                .parallel()
                .flatMap(cacheDisk -> spinDown(cacheDisk.getIdx()))
                .then();
    }

    @Override
    public Mono<Void> spinDownData() {
        return getDataDisks()
                .parallel()
                .flatMap(dataDisk -> spinDown(dataDisk.getIdx()))
                .then();
    }

    @Override
    public Mono<Void> spinUp() {
        return operations.spinUpAll();
    }

}
