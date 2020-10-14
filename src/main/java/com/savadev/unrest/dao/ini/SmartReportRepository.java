package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.disk.Disk;
import com.savadev.unrest.domain.disk.SmartReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SmartReportRepository {

    Flux<SmartReport> getReports();

    Mono<SmartReport> getReport(Disk disk);

}
