package com.savadev.unrest.dao.client.disk;

import com.savadev.unrest.config.properties.DiskOperationsProperties;
import com.savadev.unrest.dao.ini.VarRepository;
import com.savadev.unrest.domain.disk.Disk;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

public class DiskOperationsClient implements DiskOperations {

    private final DiskOperationsProperties properties;

    private final WebClient client;

    private final VarRepository varRepository;

    public DiskOperationsClient(DiskOperationsProperties properties, WebClient client, VarRepository varRepository) {
        this.properties = properties;
        this.client = client;
        this.varRepository = varRepository;
    }

    @Override
    public Mono<Void> spinDownAll() {
        return Mono.zip(varRepository.getCsrfToken(), varRepository.getMdState())
                .flatMap(objects -> client.get()
                        .uri(getSpinDownAllUrl(objects.getT1(), objects.getT2()))
                        .exchange()
                        .then());
    }

    @Override
    public Mono<Void> spinUp(Disk disk) {
        return Mono.zip(varRepository.getCsrfToken(), varRepository.getMdState())
                .flatMap(objects -> client.get()
                        .uri(getSpinUpUrl(disk.getName(), objects.getT1(), objects.getT2()))
                        .exchange()
                        .then());
    }

    @Override
    public Mono<Void> spinUpAll() {
        return Mono.zip(varRepository.getCsrfToken(), varRepository.getMdState())
                .flatMap(objects -> client.get()
                        .uri(getSpinUpAllUrl(objects.getT1(), objects.getT2()))
                        .exchange()
                        .then());
    }

    @Override
    public Mono<Void> spinDown(Disk disk) {
        return Mono.zip(varRepository.getCsrfToken(), varRepository.getMdState())
                .flatMap(objects -> client.get()
                        .uri(getSpinDownUrl(disk.getName(), objects.getT1(), objects.getT2()))
                        .exchange()
                        .then());
    }

    private String getSpinUpAllUrl(String csrf, String state) {
        Map<String, Object> params = new HashMap<>();
        params.put("csrf", csrf);
        params.put("state", state);
        return properties.getSpinUpAll()
                .expand(params).toString();
    }

    private String getSpinDownAllUrl(String csrf, String state) {
        Map<String, Object> params = new HashMap<>();
        params.put("csrf", csrf);
        params.put("state", state);
        return properties.getSpinDownAll()
                .expand(params).toString();
    }

    private String getSpinUpUrl(String name, String csrf, String state) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("csrf", csrf);
        params.put("state", state);
        return properties.getSpinUp()
                .expand(params).toString();
    }

    private String getSpinDownUrl(String name, String csrf, String state) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("csrf", csrf);
        params.put("state", state);
        return properties.getSpinDown()
                .expand(params).toString();
    }

}
