package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Library;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface LibraryDao {
    /**
     * 查询所有记录
     * @return 记录
     */
    List<Library> findAll();

    /**
     * 条件查询
     * @param condition 条件
     * @return library
     */
    List<Library> findAll(Map<String, ?> condition);

    /**
     * id 查询
     * @param id id
     * @return library
     */
    Library findById(int id);

    /**
     * 保存
     * @param library library
     */
    void save(Library library);

    /**
     * 删除
     * @param id id
     * @return
     */
    int delete(Integer id);
}
