package com.huangjinyong.library.service.impl;

import com.huangjinyong.library.dao.LibraryDao;
import com.huangjinyong.library.dao.impl.LibraryDaoImpl;
import com.huangjinyong.library.entity.Library;
import com.huangjinyong.library.service.LibraryService;

import java.util.List;

/**
 * @author huangjinyong
 */
public class LibraryServiceImpl implements LibraryService {
    private LibraryDao dao=new LibraryDaoImpl();

    @Override
    public List<Library> findAll() {
        return dao.findAll();
    }
}
