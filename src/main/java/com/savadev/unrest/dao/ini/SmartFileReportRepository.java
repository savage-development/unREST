package com.savadev.unrest.dao.ini;

import com.savadev.unrest.dao.ResourceLoader;
import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.domain.disk.SmartReport;
import org.ini4j.Ini;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class SmartFileReportRepository implements SmartReportRepository {

    private final ResourceLoader<Ini> loader;

    public SmartFileReportRepository(ResourceLoader<Ini> loader) {
        this.loader = loader;
    }

    @Override
    public Flux<SmartReport> getReports() {
        return null;
    }

    @Override
    public Mono<SmartReport> getReport(Disk disk) {
        return null;
    }
}
