package com.shzlw.horiz.s3;

import com.shzlw.horiz.LocalCacheConfig;

public abstract class AbstractS3StoreStepBuilder<StoreType>
        implements S3StoreConfigStep, S3StoreBuildStep {

    private S3Config s3Config;
    private LocalCacheConfig localCacheConfig;

    public AbstractS3StoreStepBuilder() {
    }

    public S3Config getS3Config() {
        return s3Config;
    }

    public LocalCacheConfig getLocalCacheConfig() {
        return localCacheConfig;
    }

    @Override
    public abstract StoreType build();

    @Override
    public S3StoreBuildStep s3(S3Config s3Config) {
        this.s3Config = s3Config;
        return this;
    }

    @Override
    public S3StoreBuildStep localCache(LocalCacheConfig localCacheConfig) {
        this.localCacheConfig = localCacheConfig;
        return this;
    }
}
