package com.shzlw.horiz;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class LocalCache {

    private static final long MAXIMUM_SIZE = 100;
    private static final long DURATION = 10;
    private static final TimeUnit UNIT = TimeUnit.MINUTES;

    private final Cache<String, String> cache;

    public LocalCache(LocalCacheConfig localCacheConfig) {
        long maximumSize = Utils.getOrDefault(localCacheConfig.getMaximumSize(), MAXIMUM_SIZE);
        long duration = Utils.getOrDefault(localCacheConfig.getDuration(), DURATION);
        TimeUnit unit = Utils.getOrDefault(localCacheConfig.getUnit(), UNIT);

        cache = Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterAccess(duration, unit)
                .build();
    }

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.getIfPresent(key);
    }

    public void delete(String key) {
        cache.invalidate(key);
    }

    public void deleteAll() {
        cache.invalidateAll();
    }
}
