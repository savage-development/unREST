package com.savadev.unrest.dao.ini;

import com.savadev.unrest.dao.ResourceLoader;
import com.savadev.unrest.domain.share.Share;
import org.ini4j.Ini;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

public class ShareIniRepository implements ShareRepository {

    private final ResourceLoader<Ini> loader;

    private final UserShareParser userShareParser = new UserShareParser();

    public ShareIniRepository(ResourceLoader<Ini> loader) {
        this.loader = loader;
    }

    @Override
    public Flux<Share> getShares() {
        return Mono.from(loader.load())
                .flatMapMany(ini -> Flux.fromIterable(ini.keySet()
                        .stream()
                        .map(ini::get)
                        .collect(Collectors.toSet())))
                .map(userShareParser::parse);
    }
}
