package com.savadev.unrest.domain.var;

import com.savadev.unrest.domain.Version;

public class UnraidVariables implements Variables {

    private final Version version;

    private final String timezone;

    private final String comment;

    private final String mdState;

    private final String csrfToken;

    private UnraidVariables(Builder builder) {
        this.version = builder.version;
        this.timezone = builder.timezone;
        this.comment = builder.comment;
        this.mdState = builder.mdState;
        this.csrfToken = builder.csrfToken;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Version getVersion() {
        return version;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String getMdState() {
        return mdState;
    }

    @Override
    public String getCsrfToken() {
        return csrfToken;
    }

    public static final class Builder {

        private Version version;

        private String timezone;

        private String comment;

        private String mdState;

        private String csrfToken;

        private Builder() {

        }

        public Builder withVersion(Version version) {
            this.version = version;
            return this;
        }

        public Builder withTimezone(String timezone) {
            this.timezone = timezone;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder withMdState(String mdState) {
            this.mdState = mdState;
            return this;
        }

        public Builder withCsrfToken(String csrfToken) {
            this.csrfToken = csrfToken;
            return this;
        }

        public UnraidVariables build() {
            return new UnraidVariables(this);
        }

    }

}
