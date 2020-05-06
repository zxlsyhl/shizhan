package org.zxl.shizhan.hbase.entity;

import java.util.List;

public class HbaseRow {
    private String rowKey;
    private List<HbaseCell> hbaseCells;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public HbaseRow(String rowKey, List<HbaseCell> hbaseCells) {
        this.rowKey = rowKey;
        this.hbaseCells = hbaseCells;
    }

    public HbaseRow() {
    }

    public HbaseRow(List<HbaseCell> hbaseCells) {
        this.hbaseCells = hbaseCells;
    }

    public List<HbaseCell> getHbaseCells() {
        return hbaseCells;
    }

    public void setHbaseCells(List<HbaseCell> hbaseCells) {
        this.hbaseCells = hbaseCells;
    }
}
