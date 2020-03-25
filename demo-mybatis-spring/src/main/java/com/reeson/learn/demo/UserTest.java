package com.reeson.learn.demo;


import com.reeson.learn.demo.entity.User;
import com.reeson.learn.demo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.util.List;

public class UserTest {
    UserService userService;

    void setUserService(UserService userService){
        this.userService = userService;
    }

    void selectUser(int id) {
        User user = userService.getUser(id);
        System.err.println(user);
    }

    void selectAll() {
        List<User> users = userService.getAll();
        System.err.println(users);
    }

    void insertUser(User user) {
        int insert = userService.insert(user);
        // 如果不是自动提交的话，需要使用 sqlSession.commit()
        System.err.println(insert);
    }

    void deleteUser(int id) {
        int delete = userService.delete(id);
        System.err.println(delete);
    }

    void updateUser(User user) {
        int update = userService.update(user);
        System.err.println(update);
    }

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserTest services = new UserTest();
        services.setUserService((UserService)applicationContext.getBean("userService"));
        //查询
        services.selectAll();
        services.selectUser(1);
        //添加
        User user = new User();
        user.setId(40);
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
        services.deleteUser(40);
        services.selectAll();
    }
}
