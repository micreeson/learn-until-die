package com.reeson.learn.demo.service;

import com.reeson.learn.demo.entity.User;
import com.reeson.learn.demo.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserService {
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {this.userMapper = userMapper;}

    public User getUser(int userId){
        return this.userMapper.getOne(userId);
    }
    public List<User> getAll(){
        return this.userMapper.getAll();
    }

    public int insert(User user){
        return this.userMapper.insert(user);
    }

    public int update(User user){
        return this.userMapper.update(user);
    }

    public int delete(int id){
        return this.userMapper.delete(id);
    }
}
