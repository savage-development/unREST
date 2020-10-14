package com.savadev.unrest.domain.var;

import com.savadev.unrest.domain.Version;

public interface Variables {

    Version getVersion();

    String getMdState();

    String getCsrfToken();

}
