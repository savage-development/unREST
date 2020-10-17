package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.Version;
import reactor.core.publisher.Mono;

public interface VarRepository {

    Mono<Version> getVersion();

    Mono<String> getTimezone();

    Mono<String> getComment();

    Mono<String> getMdState();

    Mono<String> getCsrfToken();

}
