package com.shzlw.horiz.s3;

import com.amazonaws.services.s3.AmazonS3;

public class S3Config {

    private final String bucketName;
    private final AmazonS3 s3Client;
    private final S3Service s3Service;

    private S3Config(StepBuilder stepBuilder) {
        this.bucketName = stepBuilder.bucketName;
        this.s3Client = stepBuilder.s3Client;

        this.s3Service = new S3Service(s3Client, bucketName);
    }

    public String getBucketName() {
        return bucketName;
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public S3Service getS3Service() {
        return s3Service;
    }

    public static ClientStep builder() {
        return new StepBuilder();
    }

    public static interface ClientStep {
        BuildStep s3Client(AmazonS3 s3Client);
    }

    public static interface BuildStep {
        BuildStep bucketName(String bucketName);

        S3Config build();
    }

    private static class StepBuilder implements ClientStep, BuildStep {

        private AmazonS3 s3Client;
        private String bucketName;

        @Override
        public BuildStep s3Client(AmazonS3 s3Client) {
            this.s3Client = s3Client;
            return this;
        }

        @Override
        public BuildStep bucketName(String bucketName) {
            this.bucketName = bucketName;
            return this;
        }

        @Override
        public S3Config build() {
            return new S3Config(this);
        }
    }
}
