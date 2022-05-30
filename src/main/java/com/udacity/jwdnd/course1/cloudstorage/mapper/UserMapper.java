package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface UserMapper {
    @Select("Select * from USERS ")
    public List<Users> findAll();
    @Insert("INSERT INTO USERS (userName, passWord, salt, firstName, lastName) VALUES (#{userName}, #{passWord}, #{salt}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true,keyProperty ="userId")
    public int insertUser(Users users);
    @Select("Select * from USERS where userName=#{userName} AND passWord=#{passWord}")
    public Users findOne(Users users);
    @Select("SELECT * FROM USERS WHERE USERNAME=#{username}")
    Users getUser(String username);
}

