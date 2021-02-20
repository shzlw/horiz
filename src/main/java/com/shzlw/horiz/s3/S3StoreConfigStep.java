package com.shzlw.horiz.s3;

public interface S3StoreConfigStep<StoreType> {

    S3StoreBuildStep<StoreType> s3(S3Config s3Config);
}
