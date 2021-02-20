package com.shzlw.horiz.s3;

import com.shzlw.horiz.KVStore;
import com.shzlw.horiz.LocalCacheConfig;

public class S3KVStore extends AbstractS3Store implements KVStore {

    public S3KVStore(LocalCacheConfig localCacheConfig, S3Config s3Config) {
        super(localCacheConfig, s3Config);
    }

    @Override
    public void put(String key, String value) {
        getS3Config().getS3Service().put(key, value);
        if (isCacheUsed()) {
            getLocalCache().put(key, value);
        }
    }

    @Override
    public String get(String key) {
        String value;
        if (isCacheUsed()) {
            value = getLocalCache().get(key);
            if (value == null) {
                value = getS3Config().getS3Service().get(key);
                if (value != null) {
                    getLocalCache().put(key, value);
                }
            }
            return value;
        } else {
            return getS3Config().getS3Service().get(key);
        }
    }

    @Override
    public void delete(String key) {
        getS3Config().getS3Service().delete(key);
        if (isCacheUsed()) {
            getLocalCache().delete(key);
        }
    }
}
