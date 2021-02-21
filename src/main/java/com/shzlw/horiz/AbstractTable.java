package com.shzlw.horiz;

public abstract class AbstractTable<T> implements Table<T> {

    private final String name;
    private final Class<T> clazz;
    private TableStore tableStore;

    protected AbstractTable(String name, Class<T> clazz) {
        this.name = name;
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
}
