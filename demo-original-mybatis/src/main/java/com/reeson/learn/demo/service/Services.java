package com.reeson.learn.demo.service;

import com.reeson.learn.demo.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Services {
    SqlSessionFactory sessionFactory = null;
    SqlSession sqlSession = null;


    public Services(){
        String resource = "mybatis-config.xml";
        // 加载mybatis的配置文件
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 构建sqlSession的工厂
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        // 创建能执行映射文件中sql的sqlSession，默认是手动提交事务的，使用自动提交的话加上参数 true
        sqlSession = sessionFactory.openSession(true);
    }

    public void selectUser(int id) {
        User user = sqlSession.selectOne("com.reeson.learn.demo.mapper.getUser", id);
        System.out.println(user);
    }

    public void selectAll() {
        List<User> users = sqlSession.selectList("com.reeson.learn.demo.mapper.getUserAll");
        System.out.println(users);
    }

    public void insertUser(User user) {
        int insert = sqlSession.insert("com.reeson.learn.demo.mapper.insertUser", user);
        // 如果不是自动提交的话，需要使用 sqlSession.commit()
        System.out.println(insert);
    }

    public void deleteUser(int id) {
        int delete = sqlSession.delete("com.reeson.learn.demo.mapper.deleteUser", id);
        System.out.println(delete);
    }

    public void updateUser(User user) {
        int update = sqlSession.update("com.reeson.learn.demo.mapper.updateUser", user);
        System.out.println(update);
    }

    public static void main(String[] args) throws IOException {
        Services services = new Services();
        //查询
        services.selectUser(1);
        services.selectAll();
        //添加
        User user = new User();
        user.setId(20);
        user.setName("Hanks");
        user.setAge(15);
        services.insertUser(user);
        services.selectAll();
        //更新
        user.setName("Grace");
        user.setAge(18);
        services.updateUser(user);
        services.selectAll();
        //删除
        services.deleteUser(20);
        services.selectAll();

    }
}
