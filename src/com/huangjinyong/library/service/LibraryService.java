package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Library;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface LibraryService {
    /**
     * 查找所有图书馆
     * @return 所有图书馆
     */
    List<Library> findAll();

    /**
     * 条件查询
     * @param condition
     * @return library
     */
    List<Library> findAll(Map<String,?> condition);

    /**
     * id查询
     * @param id id
     * @return library
     */
    Library findById(int id);

    /**
     * 分页查询所有
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 分页信息
     */
    PageBean<Library> findByPage(int currentPage,int pageSize);

    /**
     * 保存
     * @param library
     * @return 如果数据库中存在同名的返回false
     */
    int save(Library library);

    /**
     * 删除
     * @param id id
     * @return 成功返回true
     */
    boolean delete(Integer id);
}
