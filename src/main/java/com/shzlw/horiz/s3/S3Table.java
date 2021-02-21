package com.shzlw.horiz.s3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.shzlw.horiz.AbstractTable;

import java.io.IOException;
import java.util.List;

public class S3Table<T> extends AbstractTable<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public S3Table(String name, Class<T> clazz) {
        super(name, clazz);
    }

    @Override
    public S3TableStore getTableStore() {
        return getTableStore();
    }

    @Override
    public List<T> get() {
        String tableContent = getTableStore().getS3Config().getS3Service().get(getName());

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        try {
            List<T> list = objectMapper.readValue(tableContent, typeFactory.constructCollectionType(List.class, getClazz()));
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(T entity) {

        List<T> list = get();
        // Check duplicated?
        list.add(entity);

        try {
            String content = objectMapper.writeValueAsString(list);
            getTableStore().getS3Config().getS3Service().put(getName(), content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchAdd(List<T> list) {
    }

    @Override
    public void update(T entity) {

    }

    @Override
    public void batchUpdate(List<T> list) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void batchDelete(List<T> list) {

    }
}
