package com.savadev.unrest.dao;

import org.reactivestreams.Publisher;

public interface ResourceLoader<T> {

    Publisher<T> load(String resource);

}
