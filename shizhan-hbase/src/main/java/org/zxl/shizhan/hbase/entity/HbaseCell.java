package org.zxl.shizhan.hbase.entity;

public class HbaseCell {
    private String rowKey;
    private String familyName;
    private String columnName;
    private long timestamp;
    private String value;

    public HbaseCell() {
    }

    public HbaseCell(String rowKey, String familyName, String columnName, long timestamp, String value) {
        this.rowKey = rowKey;
        this.familyName = familyName;
        this.columnName = columnName;
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
