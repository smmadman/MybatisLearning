package com.junjie.dao;

import com.junjie.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */

public interface IUserDAO {
    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();
}
