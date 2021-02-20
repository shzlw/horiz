package com.shzlw.horiz;

import com.amazonaws.services.s3.AmazonS3;
import com.shzlw.horiz.s3.S3Config;
import com.shzlw.horiz.s3.S3KVStore;
import com.shzlw.horiz.s3.S3KVStoreBuilder;
import com.shzlw.horiz.s3.S3Service;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.TimeUnit;

@RunWith(PowerMockRunner.class)
@PrepareForTest(S3Config.class)
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

        S3KVStore s3KVStore = S3KVStoreBuilder.builder()
                .s3(S3Config.builder()
                        .s3Client(s3Client)
                        .bucketName(bucketName)
                        .build())
                .build();

        Assert.assertEquals(value, s3KVStore.get(key));
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

        S3KVStore s3KVStore = S3KVStoreBuilder.builder()
                .s3(S3Config.builder()
                        .s3Client(s3Client)
                        .bucketName(bucketName)
                        .build())
                .localCache(LocalCacheConfig.builder()
                        .expire(5, TimeUnit.MINUTES)
                        .maximumSize(100)
                        .build())
                .build();

        s3KVStore.put(key, value);
        Assert.assertEquals(value, s3KVStore.get(key));
        Mockito.verify(s3Service, Mockito.times(0)).get(key);
    }
}
