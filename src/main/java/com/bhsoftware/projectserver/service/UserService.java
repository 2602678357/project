package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.JPADao.JPAUserDao;
import com.bhsoftware.projectserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**增加用户
     * @param username 用户名
     * @param password 密码
     * @param email 邮箱
     * @param phone 电话
     * @param realname 真实姓名
     */
    public void addUser(String username, String password, String email,String phone,String name,String salt){
        userMapper.insertUser(username,password,email,phone,name,salt);
    }

    public User getUser(String username,String phone,String email){
        return userMapper.getUser(username,phone,email);
    }

    public User getUserByName(String username){
        return userMapper.getUserByName(username);
    }
    public User getUserPhone(String phone){
        return userMapper.getUserPhone(phone);
    }
}
