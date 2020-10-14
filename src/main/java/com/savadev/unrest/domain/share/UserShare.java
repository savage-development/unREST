package com.savadev.unrest.domain.share;

public class UserShare implements Share {

    private final String name;

    private final String originalName;

    private final String comment;

    private final String allocator;

    private final String splitLevel;

    private final String floor;

    private final String include;

    private final String exclude;

    private final String useCache;

    private final String copyOnWrite;

    private final Long free;

    private final Long size;

    private final Boolean encrypted;

    private UserShare(Builder builder) {
        this.name = builder.name;
        this.originalName = builder.originalName;
        this.comment = builder.comment;
        this.allocator = builder.allocator;
        this.splitLevel = builder.splitLevel;
        this.floor = builder.floor;
        this.include = builder.include;
        this.exclude = builder.exclude;
        this.useCache = builder.useCache;
        this.copyOnWrite = builder.copyOnWrite;
        this.free = builder.free;
        this.size = builder.size;
        this.encrypted = builder.encrypted;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getOriginalName() {
        return originalName;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getAllocator() {
        return allocator;
    }

    @Override
    public String getSplitLevel() {
        return splitLevel;
    }

    @Override
    public String getFloor() {
        return floor;
    }

    @Override
    public String getInclude() {
        return include;
    }

    @Override
    public String getExclude() {
        return exclude;
    }

    @Override
    public String getUseCache() {
        return useCache;
    }

    @Override
    public String getCopyOnWrite() {
        return copyOnWrite;
    }

    @Override
    public Long getFree() {
        return free;
    }

    @Override
    public Long getSize() {
        return size;
    }

    @Override
    public Boolean isEncrypted() {
        return encrypted;
    }

    public static final class Builder {

        private String name;

        private String originalName;

        private String comment;

        private String allocator;

        private String splitLevel;

        private String floor;

        private String include;

        private String exclude;

        private String useCache;

        private String copyOnWrite;

        private Long free;

        private Long size;

        private Boolean encrypted;

        private Builder() {

        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOriginalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder withAllocator(String allocator) {
            this.allocator = allocator;
            return this;
        }

        public Builder withSplitLevel(String splitLevel) {
            this.splitLevel = splitLevel;
            return this;
        }

        public Builder withFloor(String floor) {
            this.floor = floor;
            return this;
        }

        public Builder withInclude(String include) {
            this.include = include;
            return this;
        }

        public Builder withExclude(String exclude) {
            this.exclude = exclude;
            return this;
        }

        public Builder withUseCache(String useCache) {
            this.useCache = useCache;
            return this;
        }

        public Builder withCopyOnWrite(String copyOnWrite) {
            this.copyOnWrite = copyOnWrite;
            return this;
        }

        public Builder withFree(Long free) {
            this.free = free;
            return this;
        }

        public Builder withSize(Long size) {
            this.size = size;
            return this;
        }

        public Builder isEncrypted(boolean encrypted) {
            this.encrypted = encrypted;
            return this;
        }

        public UserShare build() {
            return new UserShare(this);
        }

    }

}
