package org.firengine.mvp.model;

public class Model {
    protected String tableName;
    //protected String[] tableColumns;
    protected String[] tableColumns;

    public String getTableName()
    {
        return tableName;
    }

    public String[] getTableColumns() {
        return tableColumns;
    }
}
