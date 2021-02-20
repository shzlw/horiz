package com.shzlw.horiz.s3;

import com.shzlw.horiz.LocalCacheConfig;

public interface S3StoreBuildStep<StoreType> {

    S3StoreBuildStep<StoreType> localCache(LocalCacheConfig localCacheConfig);

    StoreType build();
}
