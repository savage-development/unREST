package com.savadev.unrest.dao.ini;

import com.savadev.unrest.dao.ResourceLoader;
import com.savadev.unrest.domain.disk.CacheDisk;
import com.savadev.unrest.domain.disk.DataDisk;
import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.domain.disk.FlashDisk;
import com.savadev.unrest.domain.disk.ParityDisk;
import com.savadev.unrest.utils.IniUtils;
import org.ini4j.Ini;
import org.ini4j.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DiskIniRepository implements DiskRepository {

    private final ResourceLoader<Ini> loader;

    private final ParityDiskParser parityDiskParser = new ParityDiskParser();

    private final DataDiskParser dataDiskParser = new DataDiskParser();

    private final CacheDiskParser cacheDiskParser = new CacheDiskParser();

    private final FlashDiskParser flashDiskParser = new FlashDiskParser();

    public DiskIniRepository(ResourceLoader<Ini> loader) {
        this.loader = loader;
    }

    @Override
    public Flux<ParityDisk> getParity() {
        return Mono.from(loader.load("disks.ini"))
                .flatMapMany(ini -> Flux.fromStream(ini.keySet().stream()
                        .filter(s -> s.contains("parity"))
                        .map(ini::get)))
                .filter(section -> {
                    var status = getStatus(section);
                    return status != Disk.Status.DISK_NP && status != Disk.Status.DISK_NP_DSBL;
                })
                .map(parityDiskParser::parse);
    }

    @Override
    public Flux<DataDisk> getData() {
        return Mono.from(loader.load("disks.ini"))
                .flatMapMany(ini -> Flux.fromStream(ini.keySet().stream()
                        .filter(s -> s.contains("disk"))
                        .map(ini::get)))
                .filter(section -> Disk.Status.DISK_NP != getStatus(section))
                .map(dataDiskParser::parse);
    }

    @Override
    public Flux<CacheDisk> getCache() {
        return Mono.from(loader.load("disks.ini"))
                .flatMapMany(ini -> Flux.fromStream(ini.keySet().stream()
                        .filter(s -> s.contains("cache"))
                        .map(ini::get)))
                .filter(section -> Disk.Status.DISK_NP != getStatus(section))
                .map(cacheDiskParser::parse);
    }

    @Override
    public Flux<FlashDisk> getFlash() {
        return Mono.from(loader.load("disks.ini"))
                .flatMapMany(ini -> Flux.fromStream(ini.keySet().stream()
                        .filter(s -> s.contains("flash"))
                        .map(ini::get)))
                .filter(section -> Disk.Status.DISK_NP != getStatus(section))
                .map(flashDiskParser::parse);
    }

    protected Disk.Status getStatus(Profile.Section section) {
        return IniUtils.getValue(section, "status", false)
                .map(Disk.Status::valueOf)
                .orElse(null);
    }

}
