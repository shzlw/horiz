package com.shzlw.horiz.s3;

public class S3TableStoreBuilder extends AbstractS3StoreStepBuilder {

    public static S3StoreConfigStep<S3TableStore> builder() {
        return new S3TableStoreBuilder();
    }

    @Override
    public S3TableStore build() {
        return new S3TableStore(this.getLocalCacheConfig(), this.getS3Config());
    }
}
