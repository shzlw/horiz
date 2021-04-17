package com.shzlw.horiz;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.Field;
import java.util.List;

public abstract class AbstractTable<T, ID> implements Table<T, ID> {

    private final String name;
    private final String idFieldName;
    private final Class<T> clazz;
    private TableStore tableStore;

    protected AbstractTable(String name, String idFieldName, Class<T> clazz) {
        this.name = name;
        this.idFieldName = idFieldName;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public TableStore getTableStore() {
        return tableStore;
    }

    public void setTableStore(TableStore tableStore) {
        this.tableStore = tableStore;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    protected ID getIdVal(T entity) {
        String idField = this.idFieldName;
        ID val = null;
        try {
            Field field = entity.getClass().getDeclaredField(idField);
            field.setAccessible(true);
            val = (ID) field.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return val;
    }
}
