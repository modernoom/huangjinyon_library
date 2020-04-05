package com.huangjinyong.library.util.jdbchelper.page;

import java.util.List;

/**
 * @author huangjinyong
 */
public class PageBean<T> {
    //当前页
    private int currentPage;
    //每页大小
    private int pageSize;
    //总页数
    private int totalPage;
    //当期页大小
    private int currentSize;
    //结果
    private List<T> list;


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
