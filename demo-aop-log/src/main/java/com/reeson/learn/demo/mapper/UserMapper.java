package com.reeson.learn.demo.mapper;

import com.reeson.learn.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    @Results({
            @Result(property = "age",  column = "age"),
            @Result(property = "name", column = "name"),
            @Result(property = "id", column = "id")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    User getOne(int id);

    @Insert("INSERT INTO users(id,name,age) VALUES(#{id}, #{name}, #{age})")
    int insert(User user);

    @Update("UPDATE users SET name=#{name},age=#{age} WHERE id =#{id}")
    int update(User user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    int delete(int id);

}
