package com.shzlw.horiz;

import java.util.List;

public interface Table<T, ID> {

    List<T> get();

    T getById(ID id);

    int add(T entity);

    int batchAdd(List<T> list);

    int update(T entity);

    int batchUpdate(List<T> list);

    int delete(T entity);

    int batchDelete(List<T> list);
}
