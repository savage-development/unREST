package com.savadev.unrest.dao.client.docker;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.savadev.unrest.domain.docker.container.Container;

import java.time.Instant;
import java.util.Set;

public class ContainerResponse implements Container {

    private String id;

    private Set<String> names;

    private String image;

    private String imageId;

    private String command;

    private Instant created;

    private String state;

    private String status;

    protected ContainerResponse() {

    }

    @Override
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    protected void setId(String id) {
        this.id = id;
    }

    @Override
    @JsonProperty("names")
    public Set<String> getNames() {
        return names;
    }

    @JsonProperty("Names")
    protected void setNames(Set<String> names) {
        this.names = names;
    }

    @Override
    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("Image")
    protected void setImage(String image) {
        this.image = image;
    }

    @Override
    @JsonProperty("imageId")
    public String getImageId() {
        return imageId;
    }

    @JsonProperty("ImageID")
    protected void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    @JsonProperty("command")
    public String getCommand() {
        return command;
    }

    @JsonProperty("Command")
    protected void setCommand(String command) {
        this.command = command;
    }

    @Override
    @JsonProperty("created")
    public Instant getCreated() {
        return created;
    }

    @JsonProperty("Created")
    protected void setCreated(Instant created) {
        this.created = created;
    }

    @Override
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("State")
    protected void setState(String state) {
        this.state = state;
    }

    @Override
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("Status")
    protected void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContainerResponse{" +
                "id='" + id + '\'' +
                ", names=" + names +
                ", image='" + image + '\'' +
                ", imageId='" + imageId + '\'' +
                ", command='" + command + '\'' +
                ", created=" + created +
                ", state='" + state + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
