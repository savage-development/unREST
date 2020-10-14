package com.savadev.unrest.config.properties;

import org.springframework.web.util.UriTemplate;

import java.net.URI;

public class DockerOperationsProperties implements OperationsProperties {

    private String version;

    private URI socket;

    private URI allContainers;

    private UriTemplate containerById;

    private UriTemplate startContainer;

    private UriTemplate stopContainer;

    private UriTemplate restartContainer;

    private UriTemplate killContainer;

    private UriTemplate pauseContainer;

    private UriTemplate unpauseContainer;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public URI getSocket() {
        return socket;
    }

    public void setSocket(URI socket) {
        this.socket = socket;
    }

    public URI getAllContainers() {
        return allContainers;
    }

    public void setAllContainers(URI allContainers) {
        this.allContainers = allContainers;
    }

    public UriTemplate getContainerById() {
        return containerById;
    }

    public void setContainerById(UriTemplate containerById) {
        this.containerById = containerById;
    }

    public UriTemplate getStartContainer() {
        return startContainer;
    }

    public void setStartContainer(UriTemplate startContainer) {
        this.startContainer = startContainer;
    }

    public UriTemplate getStopContainer() {
        return stopContainer;
    }

    public void setStopContainer(UriTemplate stopContainer) {
        this.stopContainer = stopContainer;
    }

    public UriTemplate getRestartContainer() {
        return restartContainer;
    }

    public void setRestartContainer(UriTemplate restartContainer) {
        this.restartContainer = restartContainer;
    }

    public UriTemplate getKillContainer() {
        return killContainer;
    }

    public void setKillContainer(UriTemplate killContainer) {
        this.killContainer = killContainer;
    }

    public UriTemplate getPauseContainer() {
        return pauseContainer;
    }

    public void setPauseContainer(UriTemplate pauseContainer) {
        this.pauseContainer = pauseContainer;
    }

    public UriTemplate getUnpauseContainer() {
        return unpauseContainer;
    }

    public void setUnpauseContainer(UriTemplate unpauseContainer) {
        this.unpauseContainer = unpauseContainer;
    }
}
