package com.reeson.learn.demo.controller;

import com.reeson.learn.demo.entity.User;
import com.reeson.learn.demo.log.ControllerWebLog;
import com.reeson.learn.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
	
	@Autowired
    private UserMapper userMapper;
	
	@RequestMapping("/getUsers")
    @ControllerWebLog(name = "getUsers")
    public List<User> getUsers() {
		List<User> users=userMapper.getAll();
		System.out.println(users);
		return users;
	}
	
    @RequestMapping("/getUser")
    @ControllerWebLog(name = "getUser")
    public User getUser(int id) {
    	User user=userMapper.getOne(id);
        return user;
    }
    
    @RequestMapping("/add")
    @ControllerWebLog(name = "addUser")
    public void save(User user) {
    	userMapper.insert(user);
    }
    
    @RequestMapping(value="update")
    @ControllerWebLog(name = "udpateUser")
    public void update(User user) {
    	userMapper.update(user);
    }
    
    @RequestMapping(value="/delete/{id}")
    @ControllerWebLog(name = "deleteUser")
    public void delete(@PathVariable("id") int id) {
    	userMapper.delete(id);
    }
    
    
}