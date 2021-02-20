package com.shzlw.horiz;

public interface KVStore {

    void put(String key, String value);

    String get(String key);

    void delete(String key);
}
