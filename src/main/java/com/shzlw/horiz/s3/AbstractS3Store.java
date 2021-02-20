package com.shzlw.horiz.s3;

import com.shzlw.horiz.AbstractStore;
import com.shzlw.horiz.LocalCacheConfig;

public abstract class AbstractS3Store extends AbstractStore {

    private final S3Config s3Config;

    public AbstractS3Store(LocalCacheConfig localCacheConfig, S3Config s3Config) {
        super(localCacheConfig);
        this.s3Config = s3Config;
    }

    public S3Config getS3Config() {
        return s3Config;
    }
}
