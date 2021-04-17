package com.shzlw.horiz.s3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.shzlw.horiz.AbstractTable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class S3Table<T, ID> extends AbstractTable<T, ID> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public S3Table(String name, String idFieldName, Class<T> clazz) {
        super(name, idFieldName, clazz);
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
        return Collections.emptyList();
    }

    @Override
    public T getById(ID id) {
        List<T> list = get();
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(getIdVal(list.get(i)))) {
                return list.get(i);
            }
        }

        return null;
    }

    @Override
    public int add(T entity) {
        ID newId = getIdVal(entity);

        List<T> list = get();
        boolean isFound = false;
        for (int i = 0; i < list.size(); i++) {
            if (newId.equals(getIdVal(list.get(i)))) {
                isFound = true;
                break;
            }
        }

        if (isFound) {
            return 0;
        }

        list.add(entity);

        return overwriteList(list) ? 1 : 0;
    }

    @Override
    public int batchAdd(List<T> list) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public int batchUpdate(List<T> list) {
        return 0;
    }

    @Override
    public int delete(T entity) {
        return 0;
    }

    @Override
    public int batchDelete(List<T> list) {
        return 0;
    }

    private T isExist() {
        return null;
    }

    private boolean overwriteList(List<T> list) {
        try {
            String content = objectMapper.writeValueAsString(list);
            getTableStore().getS3Config().getS3Service().put(getName(), content);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
