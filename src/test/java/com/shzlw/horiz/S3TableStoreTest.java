package com.shzlw.horiz;

import com.amazonaws.services.s3.AmazonS3;
import com.shzlw.horiz.s3.S3Config;
import com.shzlw.horiz.s3.S3Service;
import com.shzlw.horiz.s3.S3Table;
import com.shzlw.horiz.s3.S3TableStoreBuilder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(S3Config.class)
public class S3TableStoreTest {

    public void test() throws Exception {
        String bucketName = "bucket";
        String key = "key";
        String value = "value";

        AmazonS3 s3Client = Mockito.mock(AmazonS3.class);
        S3Service s3Service = Mockito.mock(S3Service.class);
        PowerMockito.whenNew(S3Service.class).
                withArguments(s3Client, bucketName).thenReturn(s3Service);
        Mockito.when(s3Service.get(key)).thenReturn(value);

        TableStore tableStore = S3TableStoreBuilder.builder()
                .s3(S3Config.builder()
                        .s3Client(s3Client)
                        .bucketName(bucketName)
                        .build())
                .build();

        String tableName = "table1";
        tableStore.add(new S3Table<Product, Integer>(tableName, "id", Product.class));

        tableStore.table(tableName).add(new Product(1, "p1"));
    }
}
