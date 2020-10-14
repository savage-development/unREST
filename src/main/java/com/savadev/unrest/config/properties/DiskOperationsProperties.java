package com.savadev.unrest.config.properties;

import org.springframework.web.util.UriTemplate;

import java.net.URI;

public class DiskOperationsProperties implements OperationsProperties {

    private URI socket;

    private UriTemplate spinUpAll;

    private UriTemplate spinDownAll;

    private UriTemplate spinUp;

    private UriTemplate spinDown;

    @Override
    public URI getSocket() {
        return socket;
    }

    public void setSocket(URI socket) {
        this.socket = socket;
    }

    public UriTemplate getSpinUpAll() {
        return spinUpAll;
    }

    public void setSpinUpAll(UriTemplate spinUpAll) {
        this.spinUpAll = spinUpAll;
    }

    public UriTemplate getSpinDownAll() {
        return spinDownAll;
    }

    public void setSpinDownAll(UriTemplate spinDownAll) {
        this.spinDownAll = spinDownAll;
    }

    public UriTemplate getSpinUp() {
        return spinUp;
    }

    public void setSpinUp(UriTemplate spinUp) {
        this.spinUp = spinUp;
    }

    public UriTemplate getSpinDown() {
        return spinDown;
    }

    public void setSpinDown(UriTemplate spinDown) {
        this.spinDown = spinDown;
    }
}
