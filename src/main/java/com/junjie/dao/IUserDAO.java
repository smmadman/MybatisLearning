package com.junjie.dao;

import com.junjie.domain.QueryVo;
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

    /**
     * 保存user
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新user
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据Id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据Id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据QueryVo作为条件查询用户
     * @param vo
     * @return
     */
    List<User> findByVo(QueryVo vo);
}
