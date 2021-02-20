package com.shzlw.horiz;

import org.junit.Assert;
import org.junit.Test;

public class LocalCacheTest {

    @Test
    public void testDefaultCacheConfig() {
        LocalCacheConfig localCacheConfig = LocalCacheConfig.newBuilder().build();
        LocalCache localCache = new LocalCache(localCacheConfig);

        localCache.put("key1", "val1");
        Assert.assertEquals("val1", localCache.get("key1"));

        Assert.assertNull(localCache.get("key2"));

        localCache.put("key1", "val2");
        Assert.assertEquals("val2", localCache.get("key1"));

        localCache.delete("key1");
        Assert.assertNull(localCache.get("key1"));

        localCache.deleteAll();
        Assert.assertNull(localCache.get("key1"));
        Assert.assertNull(localCache.get("key2"));
    }
}
