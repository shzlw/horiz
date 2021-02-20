package com.shzlw.horiz;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class S3Service {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3Service(AmazonS3 s3Client, String bucketName) {
        this.s3Client = Objects.requireNonNull(s3Client, "s3Client must not be null");
        this.bucketName = Utils.notEmpty(bucketName, "bucketName must not be null or empty");
    }

    public void put(String objectKey, String content) {
        try {
            s3Client.putObject(bucketName, objectKey, content);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    public String get(String objectKey) {
        try {
            S3Object object = s3Client.getObject(bucketName, objectKey);
            byte[] byteArray = IOUtils.toByteArray(object.getObjectContent());
            String s = new String(byteArray, StandardCharsets.UTF_8);
            return s;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void delete(String objectKey) {
        try {
            s3Client.deleteObject(bucketName, objectKey);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }
}
