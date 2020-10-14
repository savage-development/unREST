package com.savadev.unrest.dao.file;

import com.savadev.unrest.domain.user.Shadow;
import reactor.core.publisher.Flux;

public interface ShadowRepository {

    Flux<Shadow> getShadows();

}
