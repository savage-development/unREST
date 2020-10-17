package com.savadev.unrest.domain.var;

import com.savadev.unrest.domain.Version;

public interface Variables {

    Version getVersion();

    String getTimezone();

    String getComment();

    String getMdState();

    String getCsrfToken();

}
