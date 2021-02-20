package com.shzlw.horiz.s3;

import com.shzlw.horiz.Table;

import java.util.List;

public class S3Table<T, ID> implements Table<T, ID> {

    private String name;

    public String getName() {
        return name;
    }

    public List<T> get() {
        return null;
    }

    public T getById(ID id) {
        return null;
    }

    public void add(T entity) {

    }

    public void batchAdd(List<T> list) {

    }

    public void update(T entity) {

    }

    public void batchUpdate(List<T> list) {

    }

    public void delete(T entity) {

    }

    public void batchDelete(List<T> list) {

    }
}
