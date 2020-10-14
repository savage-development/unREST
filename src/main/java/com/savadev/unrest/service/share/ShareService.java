package com.savadev.unrest.service.share;

import com.savadev.unrest.domain.share.Share;
import reactor.core.publisher.Flux;

public interface ShareService {

    Flux<Share> getShares();

}
