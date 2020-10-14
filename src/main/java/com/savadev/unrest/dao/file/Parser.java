package com.savadev.unrest.dao.file;

interface Parser<T> {

    T parse(String source);

}
