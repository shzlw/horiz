package com.shzlw.horiz;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3KVStore extends AbstractStore implements KVStore {

    private final String bucketName;
    private final AmazonS3 s3Client;
    private final String objectPrefix;
    private final S3Service s3Service;

    private S3KVStore(StepBuilder builder) {
        super(builder.localCacheConfig);

        this.bucketName = builder.bucketName;
        this.s3Client = builder.s3Client;
        this.objectPrefix = builder.objectPrefix;
        this.s3Service = new S3Service(s3Client, bucketName);
    }

    public String getBucketName() {
        return bucketName;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public String getObjectPrefix() {
        return objectPrefix;
    }

    public static S3KVStore.BucketStep newBuilder() {
        return new S3KVStore.StepBuilder();
    }

    public static interface BucketStep {
        BuildStep bucket(String name);
    }

    public static interface BuildStep {
        BuildStep s3Client(AmazonS3 s3Client);

        BuildStep objectPrefix(String objectPrefix);

        BuildStep localCache(LocalCacheConfig localCacheConfig);

        S3KVStore build();
    }

    private static class StepBuilder implements BucketStep, BuildStep {

        private AmazonS3 s3Client;
        private String bucketName;
        private String objectPrefix;
        private LocalCacheConfig localCacheConfig;

        @Override
        public S3KVStore.BuildStep bucket(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        @Override
        public S3KVStore.BuildStep s3Client(AmazonS3 s3Client) {
            this.s3Client = s3Client;
            return this;
        }

        @Override
        public S3KVStore.BuildStep objectPrefix(String objectPrefix) {
            this.objectPrefix = objectPrefix;
            return this;
        }

        @Override
        public BuildStep localCache(LocalCacheConfig localCacheConfig) {
            this.localCacheConfig = localCacheConfig;
            return this;
        }

        @Override
        public S3KVStore build() {
            if (s3Client == null) {
                s3Client = AmazonS3ClientBuilder.defaultClient();
            }
            return new S3KVStore(this);
        }
    }

    @Override
    public void put(String key, String value) {
        s3Service.put(key, value);
        if (isCacheUsed()) {
            getLocalCache().put(key, value);
        }
    }

    @Override
    public String get(String key) {
        String value;
        if (isCacheUsed()) {
            value = getLocalCache().get(key);
            if (value != null) {
                return value;
            } else {
                value = s3Service.get(key);
                if (value != null) {
                    getLocalCache().put(key, value);
                }
                return value;
            }
        } else {
            return s3Service.get(key);
        }
    }

    @Override
    public void delete(String key) {
        s3Service.delete(key);
        if (isCacheUsed()) {
            getLocalCache().delete(key);
        }
    }
}
