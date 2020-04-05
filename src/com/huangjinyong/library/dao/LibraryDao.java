package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Library;

import java.util.List;

/**
 * @author huangjinyong
 */
public interface LibraryDao {
    /**
     * 查询所有记录
     * @return 记录
     */
    List<Library> findAll();
}
