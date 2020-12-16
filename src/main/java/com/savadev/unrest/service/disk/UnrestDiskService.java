package com.savadev.unrest.service.disk;

import com.savadev.unrest.dao.client.disk.DiskOperations;
import com.savadev.unrest.dao.ini.DiskRepository;
import com.savadev.unrest.domain.disk.CacheDisk;
import com.savadev.unrest.domain.disk.DataDisk;
import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.domain.disk.FlashDisk;
import com.savadev.unrest.domain.disk.ParityDisk;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

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

    private <X extends Exception> Mono<Disk> getDisk(Predicate<Disk> predicate, Supplier<X> supplier) {
        return getDisks()
                .filter(predicate)
                .switchIfEmpty(Mono.error(supplier.get()))
                .next();
    }

    @Override
    public Mono<Disk> getDiskById(int idx) {
        return getDisk(Disks.byIdx(idx), () -> new DiskServiceException(HttpStatus.NOT_FOUND, String.format("Disk: %s not found.", idx)));
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
        return getDiskById(idx)
                .flatMap(disk -> {
                    if (Disks.isFlash().test(disk)) {
                        return Mono.error(new DiskServiceException(HttpStatus.BAD_REQUEST, "Cannot spin up flash drives"));
                    }
                    return operations.spinUp(disk);
                });
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
        return getDiskById(idx)
                .flatMap(disk -> {
                    if (Disks.isFlash().test(disk)) {
                        return Mono.error(new DiskServiceException(HttpStatus.BAD_REQUEST, "Cannot spin down flash drives"));
                    }
                    return operations.spinDown(disk);
                });
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
