package com.shzlw.horiz;

public abstract class AbstractStore {

    private final LocalCacheConfig localCacheConfig;
    private LocalCache localCache;

    public AbstractStore(LocalCacheConfig localCacheConfig) {
        this.localCacheConfig = localCacheConfig;
        if (localCacheConfig != null) {
            localCache = new LocalCache(localCacheConfig);
        }
    }

    public LocalCacheConfig getLocalCacheConfig() {
        return localCacheConfig;
    }

    public void clearCache() {
        if (isCacheUsed()) {
            localCache.deleteAll();
        }
    }

    protected boolean isCacheUsed() {
        return localCacheConfig != null;
    }

    protected LocalCache getLocalCache() {
        return localCache;
    }
}
