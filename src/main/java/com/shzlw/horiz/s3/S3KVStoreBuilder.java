package com.shzlw.horiz.s3;

public class S3KVStoreBuilder extends AbstractS3StoreStepBuilder {

    public static S3StoreConfigStep<S3KVStore> builder() {
        return new S3KVStoreBuilder();
    }

    @Override
    public S3KVStore build() {
        return new S3KVStore(this.getLocalCacheConfig(), this.getS3Config());
    }
}
