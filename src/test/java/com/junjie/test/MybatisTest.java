package com.junjie.test;

import com.junjie.dao.IUserDAO;
import com.junjie.domain.QueryVo;
import com.junjie.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MybatisTest {

    private InputStream in = null;
    private SqlSession sqlSession = null;
    private IUserDAO userDAO;

    @Before //单元测试执行前，执行
    public void init() throws Exception{
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDAO = sqlSession.getMapper(IUserDAO.class);
    }

    @After //单元测试执行后，执行
    public void destroy() throws  Exception{
        //6.释放资源
        try {
            if(sqlSession != null){
                sqlSession.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if(in != null){
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 入门案例
     * @param args
     */
    public void testFindAll() throws Exception{
//        //1.读取配置文件
//        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        //2.创建SqlSessionFactory工厂
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(in);
//        //3.使用工厂使用工厂生产SqlSession对象
//        SqlSession session = factory.openSession();
//        //4.使用SqlSession创建Dao接口的代理对象
//        IUserDAO userDAO = session.getMapper(IUserDAO.class);

        //5，使用代理对象执行方法
        List<User> users = userDAO.findAll();
        for(User user : users){
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("济南大学");
        user.setAddress("山东省济南市");
        user.setSex("男");
        user.setBirthday(new Date(11111111111111L));

        System.out.println("保存操作之前：" + user);
        //5，执行保存操作
        userDAO.saveUser(user);
        sqlSession.commit();

        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(52);
        user.setUsername("Update user");
        user.setAddress("山东省济南市");
        user.setSex("男");
        user.setBirthday(new Date(11111111111111L));

        //5.执行更新操作
        userDAO.updateUser(user);
        sqlSession.commit();
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        User user = new User();
        user.setId(52);
        user.setUsername("Update user");
        user.setAddress("山东省济南市");
        user.setSex("男");
        user.setBirthday(new Date(11111111111111L));

        //5.执行删除操作
        userDAO.deleteUser(52);
        sqlSession.commit();
    }

    /**
     * 测试根据Id查询用户
     */
    @Test
    public void testFindById(){

        //5.执行查询操作
        User user = userDAO.findById(45);
        System.out.println(user);
    }

    /**
     * 测试根据Name模糊查询用户
     */
    @Test
    public void testFindByName(){

        //5.执行查询操作
        List<User> users = userDAO.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试查询总用户数
     */
    @Test
    public void testFindTotal(){
        //5.执行查询操作
        System.out.println(userDAO.findTotal());
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        //5.执行查询操作
        List<User> users = userDAO.findByVo(vo);
        for (User u : users) {
            System.out.println(u);
        }
    }
}
