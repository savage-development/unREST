package com.savadev.unrest.dao.ini;

import com.savadev.unrest.domain.Version;
import com.savadev.unrest.domain.var.Variables;
import org.ini4j.Config;
import reactor.core.publisher.Mono;

public class VarIniRepository implements VarRepository {

    private final Config config;

    private final StateResourceLoader loader;

    private final VariableParser parser = new VariableParser();

    public VarIniRepository(Config config, StateResourceLoader loader) {
        this.config = config;
        this.loader = loader;
    }

    @Override
    public Mono<Version> getVersion() {
        return loader.load("var.ini", config)
                .map(ini -> parser.parse(ini.get("?")))
                .map(Variables::getVersion);
    }

    @Override
    public Mono<String> getMdState() {
        return loader.load("var.ini", config)
                .map(ini -> parser.parse(ini.get("?")))
                .map(Variables::getMdState);
    }

    @Override
    public Mono<String> getCsrfToken() {
        return loader.load("var.ini", config)
                .map(ini -> parser.parse(ini.get("?")))
                .map(Variables::getCsrfToken);
    }

}
