package com.shzlw.horiz;

import com.amazonaws.services.s3.AmazonS3;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.TimeUnit;

@RunWith(PowerMockRunner.class)
@PrepareForTest(S3KVStore.class)
public class S3KVStoreTest {

    @Test
    public void test_withNoCache() throws Exception {
        String bucketName = "bucket";
        String key = "key";
        String value = "value";

        AmazonS3 s3Client = Mockito.mock(AmazonS3.class);
        S3Service s3Service = Mockito.mock(S3Service.class);
        PowerMockito.whenNew(S3Service.class).
                withArguments(s3Client, bucketName).thenReturn(s3Service);
        Mockito.when(s3Service.get(key)).thenReturn(value);

        KVStore kvStore = S3KVStore.newBuilder()
                .bucket(bucketName)
                .s3Client(s3Client)
                .build();

        Assert.assertEquals(value, kvStore.get(key));
        Mockito.verify(s3Service, Mockito.times(1)).get(key);
    }

    @Test
    public void test_withCache() throws Exception {
        String bucketName = "bucket";
        String key = "key";
        String value = "value";

        AmazonS3 s3Client = Mockito.mock(AmazonS3.class);
        S3Service s3Service = Mockito.mock(S3Service.class);
        PowerMockito.whenNew(S3Service.class).
                withArguments(s3Client, bucketName).thenReturn(s3Service);
        Mockito.when(s3Service.get(key)).thenReturn(value);

        KVStore kvStore = S3KVStore.newBuilder()
                .bucket(bucketName)
                .s3Client(s3Client)
                .localCache(LocalCacheConfig.newBuilder()
                        .expire(5, TimeUnit.MINUTES)
                        .maximumSize(100)
                        .build())
                .build();

        kvStore.put(key, value);
        Assert.assertEquals(value, kvStore.get(key));
        Mockito.verify(s3Service, Mockito.times(0)).get(key);
    }
}
