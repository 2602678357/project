package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.JPADao.JPAUserDao;
import com.bhsoftware.projectserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JPAUserDao jpaUserMapper;

    public User LoginUser(String username, String password){
        return userMapper.getUserByUsernameAndPassword(username,password);
    }

    public User LoginUserTest(String username, String password){
        return userMapper.getUserByUsernameAndPasswordTest(username,password);
    }

    public User LoginJpa(String username, String password){
        return jpaUserMapper.getByUsernameAndPassword(username,password);
    }

    public List<User> getListUser(String username){
        return userMapper.getListUser(username);
    }
}
