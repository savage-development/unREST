package com.savadev.unrest.controller.disk;

import com.savadev.unrest.service.disk.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/disks")
public class DisksController {

    private final DiskService diskService;

    @Autowired
    public DisksController(DiskService diskService) {
        this.diskService = diskService;
    }

    @GetMapping
    Mono<GetDisksResponse> getDevices() {
        return Mono.zip(diskService.getCacheDisks().collect(Collectors.toSet()),
                diskService.getDataDisks().collect(Collectors.toSet()),
                diskService.getFlashDisks().collect(Collectors.toSet()),
                diskService.getParityDisks().collect(Collectors.toSet()))
                .map(objects -> new GetDisksResponse(
                        objects.getT1(),
                        objects.getT2(),
                        objects.getT3(),
                        objects.getT4()
                ));
    }

    @GetMapping("/parity")
    Mono<GetParityDisksResponse> getParity() {
        return diskService.getParityDisks()
                .collect(Collectors.toSet())
                .map(GetParityDisksResponse::new);
    }

    @GetMapping("/data")
    Mono<GetDataDisksResponse> getData() {
        return diskService.getDataDisks()
                .collect(Collectors.toSet())
                .map(GetDataDisksResponse::new);
    }

    @GetMapping("/cache")
    Mono<GetCacheDisksResponse> getCache() {
        return diskService.getCacheDisks()
                .collect(Collectors.toSet())
                .map(GetCacheDisksResponse::new);
    }

    @GetMapping("/flash")
    Mono<GetFlashDisksResponse> getFlash() {
        return diskService.getFlashDisks()
                .collect(Collectors.toSet())
                .map(GetFlashDisksResponse::new);
    }

    @PostMapping("/up")
    Mono<Void> spinAllUp() {
        return diskService.spinUp();
    }

    @PostMapping("/down")
    Mono<Void> spinAllDown() {
        return diskService.spinDown();
    }

    @PostMapping("/parity/up")
    Mono<Void> spinParityUp() {
        return diskService.spinUpParity();
    }

    @PostMapping("/parity/down")
    Mono<Void> spinParityDown() {
        return diskService.spinDownParity();
    }

    @PostMapping("/data/up")
    Mono<Void> spinDataUp() {
        return diskService.spinUpData();
    }

    @PostMapping("/data/down")
    Mono<Void> spinDataDown() {
        return diskService.spinDownData();
    }

    @PostMapping("/cache/up")
    Mono<Void> spinCacheUp() {
        return diskService.spinUpCache();
    }

    @PostMapping("/cache/down")
    Mono<Void> spinCacheDown() {
        return diskService.spinDownCache();
    }

}
