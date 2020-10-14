package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.share.Share;
import reactor.core.publisher.Flux;

public interface ShareRepository {

    Flux<Share> getShares();

}
