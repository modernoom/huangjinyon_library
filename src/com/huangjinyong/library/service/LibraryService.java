package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Library;

import java.util.List;

/**
 * @author huangjinyong
 */
public interface LibraryService {
    /**
     * 查找所有图书馆
     * @return 所有图书馆
     */
    List<Library> findAll();
}
