package com.huangjinyong.library.util.jdbchelper.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangjinyong
 */
public class PageHelper {
    private int fromIndex;
    private int toIndex;
    private int totalRecord;
    private int totalPage;


    public <T> PageBean<T> doPage(int currentPage, int pageSize, Support<T> support){
        List<T> list=new ArrayList();
        PageBean<T> page = new PageBean();
        List<T> result = support.doSupport();
        totalRecord=result.size();
        totalPage=totalRecord % pageSize==0?totalRecord/pageSize:(totalRecord/pageSize)+1;
        fromIndex=(currentPage-1)*pageSize;
        toIndex=fromIndex+pageSize;
        if(result.size()<=fromIndex){
            page.setList(list);
            page.setCurrentPage(currentPage);
            page.setCurrentSize(pageSize);
            page.setCurrentSize(0);
            page.setTotalPage(totalPage);
        }else if(result.size()<toIndex){
             list.addAll(result.subList(fromIndex,result.size()));
             page.setList(list);
             page.setCurrentPage(currentPage);
             page.setCurrentSize(pageSize);
             page.setCurrentSize(result.size()-fromIndex);
             page.setTotalPage(totalPage);
        }else{
            list.addAll(result.subList(fromIndex,toIndex));
            page.setList(list);
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            page.setCurrentSize(pageSize);
            page.setTotalPage(totalPage);
        }
        return page;
    }

}
