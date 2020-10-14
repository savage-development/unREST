package com.savadev.unrest.dao.client.disk;

import com.savadev.unrest.domain.disk.Disk;
import reactor.core.publisher.Mono;

public interface DiskOperations {

    Mono<Void> spinUp(Disk disk);

    Mono<Void> spinUpAll();

    Mono<Void> spinDown(Disk disk);

    Mono<Void> spinDownAll();

}
