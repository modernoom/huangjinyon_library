package com.huangjinyong.library.dao;

import com.huangjinyong.library.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * @author huangjinyong
 */
public interface CommentDao {
    /**
     * 按条件查询所有
     * @param map 条件
     * @return 评论
     */
    List<Comment> findAll(Map<String,?> map);

    /**
     * 保存
     * @param comment 评论
     */
    void save(Comment comment);
}
