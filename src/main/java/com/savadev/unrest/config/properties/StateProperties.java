package com.savadev.unrest.config.properties;

import java.net.URI;

public class StateProperties {

    private URI disks;

    private URI var;

    private URI shares;

    public URI getDisks() {
        return disks;
    }

    public void setDisks(URI disks) {
        this.disks = disks;
    }

    public URI getVar() {
        return var;
    }

    public void setVar(URI vars) {
        this.var = vars;
    }

    public URI getShares() {
        return shares;
    }

    public void setShares(URI shares) {
        this.shares = shares;
    }

}
