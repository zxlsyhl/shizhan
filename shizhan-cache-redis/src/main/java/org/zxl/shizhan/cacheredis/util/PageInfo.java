package org.zxl.shizhan.cacheredis.util;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T> implements Serializable {
    private long pageSize = 10;
    private long pageNo;
    private long totalNum;
    private long totalPage;

    private List<T> objList;

    public List<T> getObjList() {
        return objList;
    }

    public void setObjList(List<T> objList) {
        this.objList = objList;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public PageInfo() {
    }

    public PageInfo(long pageSize, long pageNo, long totalNum, long totalPage) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalNum = totalNum;
        this.totalPage = totalPage;
    }

    public PageInfo(long pageSize, long pageNo, long totalNum) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalNum = totalNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNo() {
        return pageNo;
    }

    public void setPageNo(long pageNo) {
        this.pageNo = pageNo;
    }

    public long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(long totalNum) {
        this.totalNum = totalNum;
    }

    public PageInfo(long pageSize, long pageNo, long totalNum, long totalPage, List<T> objList) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalNum = totalNum;
        this.totalPage = totalPage;
        this.objList = objList;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                ", totalNum=" + totalNum +
                ", totalPage=" + totalPage +
                ", objList=" + objList +
                '}';
    }
}
