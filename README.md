# horiz

[![Build Status](https://travis-ci.com/shzlw/horiz.svg?branch=main)](https://travis-ci.com/shzlw/horiz)
[![codecov](https://codecov.io/gh/shzlw/horiz/branch/main/graph/badge.svg)](https://codecov.io/gh/shzlw/horiz)

## Build
```sh
./gradlew build
```

## Internal dependencies
```
implementation 'com.github.ben-manes.caffeine:caffeine:2.9.0'
implementation 'com.amazonaws:aws-java-sdk-s3:1.11.957'
```

## Example
```java
// Create a new S3KVStore
S3KVStore s3KVStore = S3KVStoreBuilder.builder()
    .s3(S3Config.builder()
        .s3Client(AmazonS3ClientBuilder.defaultClient())
        .bucketName("mybucket")
        .build())
    .localCache(LocalCacheConfig.builder()
        .expire(5, TimeUnit.MINUTES)
        .maximumSize(100)
        .build())
    .build();

// Put, Get, Delete
s3KVStore.put("key", "val");
String val = s3KVStore.get("key");
s3KVStore.delete("key");
```