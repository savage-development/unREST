package com.savadev.unrest.service.share;

import com.savadev.unrest.dao.ini.ShareRepository;
import com.savadev.unrest.domain.share.Share;
import reactor.core.publisher.Flux;

public class UnrestShareService implements ShareService {

    private final ShareRepository repository;

    public UnrestShareService(ShareRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Share> getShares() {
        return repository.getShares();
    }

}
