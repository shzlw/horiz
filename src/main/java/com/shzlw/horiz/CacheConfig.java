package com.shzlw.horiz;

import java.util.concurrent.TimeUnit;

public class CacheConfig {

    private final long maximumSize;
    private final long duration;
    private final TimeUnit unit;

    private CacheConfig(Builder builder) {
        this.maximumSize = builder.maximumSize;
        this.duration = builder.duration;
        this.unit = builder.unit;
    }

    public long getMaximumSize() {
        return maximumSize;
    }

    public long getDuration() {
        return duration;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {

        private long maximumSize;
        private long duration;
        private TimeUnit unit;

        public Builder maximumSize(long maximumSize) {
            this.maximumSize = maximumSize;
            return this;
        }

        public Builder expire(long duration, TimeUnit unit) {
            this.duration = duration;
            this.unit = unit;
            return this;
        }

        public CacheConfig build() {
            return new CacheConfig(this);
        }
    }


}
