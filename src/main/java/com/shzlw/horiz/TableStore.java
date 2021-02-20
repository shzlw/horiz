package com.shzlw.horiz;

public interface TableStore {

    void add(String name);

    void delete(String name);

    Table table(String name);
}
