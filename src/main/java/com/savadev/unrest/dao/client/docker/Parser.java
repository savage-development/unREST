package com.savadev.unrest.dao.client.docker;

import org.springframework.web.reactive.function.client.WebClient;

public interface Parser<T> {

    T parse(String version, WebClient.ResponseSpec response);

}
