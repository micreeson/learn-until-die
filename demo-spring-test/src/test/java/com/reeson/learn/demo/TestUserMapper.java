package com.reeson.learn.demo;

import com.reeson.learn.demo.entity.User;
import com.reeson.learn.demo.mapper.UserMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestUserMapper {
    @Autowired
    UserMapper userMapper;

    private final int MAX_USERS = 3;

    List<User> userList;

    @Before
    public void initTest(){
        userList = new ArrayList<>();

        for (int i = 0; i< MAX_USERS; i++){
            User user = new User();
            user.setId(i);
            user.setName("Name"+i);
            user.setAge(10+i);
            userList.add(user);
        }

        userMapper.deleteAll();
        userList.forEach(user1->userMapper.insert(user1));
    }

    @Test
    public void testGetUser(){
        int idForTest = 0;
        User user = userMapper.getOne(idForTest);
        Assert.assertEquals(userList.get(idForTest).getName(), user.getName());
        Assert.assertEquals(userList.get(idForTest).getAge(), user.getAge());
    }

    @After
    public void destoryTest(){
        userMapper.deleteAll();
    }
}
