package com.savadev.unrest.config.properties;

import java.net.URI;

public class UserProperties {

    private URI passwd;

    private URI group;

    private URI shadow;

    public URI getPasswd() {
        return passwd;
    }

    public void setPasswd(URI passwd) {
        this.passwd = passwd;
    }

    public URI getGroup() {
        return group;
    }

    public void setGroup(URI group) {
        this.group = group;
    }

    public URI getShadow() {
        return shadow;
    }

    public void setShadow(URI shadow) {
        this.shadow = shadow;
    }
}
