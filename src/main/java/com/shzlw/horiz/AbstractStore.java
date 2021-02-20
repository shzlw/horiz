package com.shzlw.horiz;

public abstract class AbstractStore {

    private final CacheConfig cacheConfig;
    private LocalCache localCache;

    public AbstractStore(CacheConfig cacheConfig) {
        this.cacheConfig = cacheConfig;
        if (cacheConfig != null) {
            localCache = new LocalCache(cacheConfig);
        }
    }

    public CacheConfig getCacheConfig() {
        return cacheConfig;
    }

    protected boolean isCacheUsed() {
        return cacheConfig != null;
    }

    protected LocalCache getLocalCache() {
        return localCache;
    }
}
