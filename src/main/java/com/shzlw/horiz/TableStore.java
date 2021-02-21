package com.shzlw.horiz;

public interface TableStore<T extends AbstractTable> {

    void add(T table);

    void delete(String name);

    T table(String name);
}
