package com.reeson.learn.demo;

import com.reeson.learn.demo.controller.UserController;
import com.reeson.learn.demo.entity.User;
import com.reeson.learn.demo.mapper.UserMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestUserController {
    @Spy
    private UserMapper userMockMapper;

    @Autowired
    @InjectMocks
    private UserController userController;

    private final int MAX_USERS = 3;

    List<User> userList;

    @Before
    public void initUserList(){
        userList = new ArrayList<>();

        for (int i = 0; i< MAX_USERS; i++){
            User user = new User();
            user.setId(i);
            user.setName("Name"+i);
            user.setAge(10+i);
            userList.add(user);
        }

        MockitoAnnotations.initMocks(this);
        User userMock = new User();
        userMock.setId(3);
        userMock.setName("mockname");
        userMock.setAge(13);
        when(userMockMapper.getOne(1)).thenReturn(userMock);
        when(userMockMapper.getOne(2)).thenReturn(userMock);
    }

    @Test
    public void testUserControllerUsingMock(){
        User userFromController = userController.getUser(1);
        Assert.assertEquals("mockname", userFromController.getName());
        Assert.assertEquals(13, userFromController.getAge());
    }


    @Test
    public void testGetUserInController(){
        int idForTest = 2;
        User user = userController.getUser(idForTest);
        Assert.assertEquals(userList.get(idForTest).getName(), user.getName());
        Assert.assertEquals(userList.get(idForTest).getAge(), user.getAge());
    }


    @After
    public void destroyUserList(){
//        userList.forEach(user1->userMapper.delete(user1.getId()));
    }
}
