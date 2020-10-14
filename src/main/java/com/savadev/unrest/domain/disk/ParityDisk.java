package com.savadev.unrest.domain.disk;

public class ParityDisk implements Disk {

    private final Integer idx;

    private final String name;

    private final String device;

    private final String id;

    private final Long size;

    private final Status status;

    private final Boolean rotational;

    private final String format;

    private final Integer temperature;

    private final Long reads;

    private final Long writes;

    private final Long errors;

    private final Long spinDownDelay;

    private final String spinUpGroup;

    private ParityDisk(Builder builder) {
        this.idx = builder.idx;
        this.name = builder.name;
        this.device = builder.device;
        this.id = builder.id;
        this.size = builder.size;
        this.status = builder.status;
        this.rotational = builder.rotational;
        this.format = builder.format;
        this.temperature = builder.temperature;
        this.reads = builder.reads;
        this.writes = builder.writes;
        this.errors = builder.errors;
        this.spinDownDelay = builder.spinDownDelay;
        this.spinUpGroup = builder.spinUpGroup;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Integer getIdx() {
        return idx;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDevice() {
        return device;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public Boolean isRotational() {
        return rotational;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public Integer getTemperature() {
        return temperature;
    }

    @Override
    public Long getReads() {
        return reads;
    }

    @Override
    public Long getWrites() {
        return writes;
    }

    @Override
    public Long getErrors() {
        return errors;
    }

    @Override
    public Long getSpinDownDelay() {
        return spinDownDelay;
    }

    @Override
    public String getSpinUpGroup() {
        return spinUpGroup;
    }

    public static final class Builder {

        private Integer idx;

        private String name;

        private String device;

        private String id;

        private Long size;

        private Status status;

        private Boolean rotational;

        private String format;

        private Integer temperature;

        private Long reads;

        private Long writes;

        private Long errors;

        private Long spinDownDelay;

        private String spinUpGroup;

        private Builder() {

        }

        public Builder withIdx(Integer idx) {
            this.idx = idx;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDevice(String device) {
            this.device = device;
            return this;
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withSize(Long size) {
            this.size = size;
            return this;
        }

        public Builder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder isRotational(boolean rotational) {
            this.rotational = rotational;
            return this;
        }

        public Builder withFormat(String format) {
            this.format = format;
            return this;
        }

        public Builder withTemperature(Integer temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder withReads(Long reads) {
            this.reads = reads;
            return this;
        }

        public Builder withWrites(Long writes) {
            this.writes = writes;
            return this;
        }

        public Builder withErrors(Long errors) {
            this.errors = errors;
            return this;
        }

        public Builder withSpinDownDelay(Long spinDownDelay) {
            this.spinDownDelay = spinDownDelay;
            return this;
        }

        public Builder withSpinUpGroup(String spinUpGroup) {
            this.spinUpGroup = spinUpGroup;
            return this;
        }

        public ParityDisk build() {
            return new ParityDisk(this);
        }

    }

}
