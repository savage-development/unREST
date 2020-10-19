package com.savadev.unrest.controller.disk;

import com.savadev.unrest.service.disk.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/disk")
public class DiskController {

    private final DiskService diskService;

    @Autowired
    public DiskController(DiskService diskService) {
        this.diskService = diskService;
    }

    @PostMapping("{id}/up")
    Mono<Void> spinUp(@PathVariable("id") int id) {
        return diskService.spinUp(id);
    }

    @PostMapping("{id}/down")
    Mono<Void> spinDown(@PathVariable("id") int id) {
        return diskService.spinDown(id);
    }

}
