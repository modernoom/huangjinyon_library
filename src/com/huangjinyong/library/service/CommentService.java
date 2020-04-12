package com.huangjinyong.library.service;

import com.huangjinyong.library.entity.Comment;
import com.huangjinyong.library.util.jdbchelper.page.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface CommentService {
    /***
     * 分页
     * @param currentPage
     * @param pageSize
     * @param conditon
     * @return
     */
    PageBean<Comment> findByPage(int currentPage,int pageSize,Map<String,?> conditon);

    /**
     * 查询所有
     * @param map 条件
     * @return comment
     */
    List<Comment> findByCondition(Map map);

    /**
     * 保存评论
     * @param comment 评论
     */
    void save(Comment comment);
}
