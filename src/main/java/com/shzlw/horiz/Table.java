package com.shzlw.horiz;

import java.util.List;

public interface Table<T> {

    List<T> get();

    void add(T entity);

    void batchAdd(List<T> list);

    void update(T entity);

    void batchUpdate(List<T> list);

    void delete(T entity);

    void batchDelete(List<T> list);
}
