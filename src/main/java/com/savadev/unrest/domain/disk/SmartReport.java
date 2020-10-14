package com.savadev.unrest.domain.disk;

import java.util.Set;

public class SmartReport {

    private final Boolean passed;

    private final Set<Attribute> attributes;

    public SmartReport(Boolean passed, Set<Attribute> attributes) {
        this.passed = passed;
        this.attributes = attributes;
    }

    public Boolean getPassed() {
        return passed;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public static final class Attribute {

        private final Long id;

        private final String name;

        private final Long flag;

        private final Long value;

        private final Long worst;

        private final Long threshold;

        private final String type;

        private final String updated;

        private final String failed;

        private final Long raw;

        public Attribute(Object[] attrs) {
            this(((Long) attrs[0]),
                    ((String) attrs[1]),
                    ((Long) attrs[2]),
                    ((Long) attrs[3]),
                    ((Long) attrs[4]),
                    ((Long) attrs[5]),
                    ((String) attrs[6]),
                    ((String) attrs[7]),
                    ((String) attrs[8]),
                    ((Long) attrs[9]));
        }

        public Attribute(Long id, String name, Long flag, Long value, Long worst, Long threshold, String type, String updated, String failed, Long raw) {
            this.id = id;
            this.name = name;
            this.flag = flag;
            this.value = value;
            this.worst = worst;
            this.threshold = threshold;
            this.type = type;
            this.updated = updated;
            this.failed = failed;
            this.raw = raw;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Long getFlag() {
            return flag;
        }

        public Long getValue() {
            return value;
        }

        public Long getWorst() {
            return worst;
        }

        public Long getThreshold() {
            return threshold;
        }

        public String getType() {
            return type;
        }

        public String getUpdated() {
            return updated;
        }

        public String getFailed() {
            return failed;
        }

        public Long getRaw() {
            return raw;
        }
    }

}
