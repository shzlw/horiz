package com.shzlw.horiz.s3;

import com.shzlw.horiz.LocalCacheConfig;
import com.shzlw.horiz.TableStore;

import java.util.HashMap;
import java.util.Map;

public class S3TableStore extends AbstractS3Store implements TableStore<S3Table> {

    private Map<String, S3Table> s3TableMap = new HashMap<>();

    public S3TableStore(LocalCacheConfig localCacheConfig, S3Config s3Config) {
        super(localCacheConfig, s3Config);
    }

    @Override
    public void add(S3Table table) {
        table.setTableStore(this);
        s3TableMap.put(table.getName(), table);
    }

    @Override
    public void delete(String name) {
        S3Table s3Table = s3TableMap.get(name);
        if (s3Table != null) {
            s3Table.setTableStore(null);
            s3TableMap.remove(name);
        }
        getS3Config().getS3Service().delete(name);
    }

    @Override
    public S3Table table(String name) {
        return s3TableMap.get(name);
    }
}
