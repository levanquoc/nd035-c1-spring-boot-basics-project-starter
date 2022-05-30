package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service

public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HashService hashService;
    public int insertUser(Users users){
        SecureRandom random= new SecureRandom();
        byte[] salt=new byte[16];
        random.nextBytes(salt);
        String encodedSalt= Base64.getEncoder().encodeToString(salt);
        String hashedPassword=hashService.getHashedValue(users.getPassWord(), encodedSalt);
        users.setSalt(encodedSalt);
        users.setPassWord(hashedPassword);
        return userMapper.insertUser(users);
    }



}
