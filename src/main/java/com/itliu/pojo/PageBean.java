package com.itliu.pojo;

import java.util.List;

/**
 * @create 2023-03-16-19:47
 */
//分页查询的PageBean
public class PageBean<T> {
    //总条数
    private int totalCount;
    //当前页数
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
