package com.savadev.unrest.dao.ini;

import org.ini4j.Profile;

interface Parser<T> {

    T parse(Profile.Section section);

}
