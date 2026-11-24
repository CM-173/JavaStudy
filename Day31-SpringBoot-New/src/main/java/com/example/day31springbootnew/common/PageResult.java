package com.example.day31springbootnew.common;

import java.util.List;

public class PageResult<T> {
    private Long total;      // 总条数（COUNT(*) 返回 Long）
    private Integer page;    // 当前页
    private Integer size;    // 每页条数
    private List<T> list;    // 当前页数据

    // 构造方法（方便创建）
    public PageResult(Long total, Integer page, Integer size, List<T> list) {
        this.total = total;
        this.page = page;
        this.size = size;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}