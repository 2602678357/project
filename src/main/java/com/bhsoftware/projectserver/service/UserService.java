package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.JPADao.JPAUserDao;
import com.bhsoftware.projectserver.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JPAUserDao jpaUserMapper;

    /**
     * 注入mongdb操作
     */

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据用户名和密码得到用户
     * @param username
     * @param password
     * @return
     */
    public User LoginUser(String username, String password){
        return userMapper.getUserByUsernameAndPassword(username,password);
    }

    /**
     * 根据用户名和密码得到用户
     * @param username
     * @param password
     * @return
     */
    public User LoginUserTest(String username, String password){
        return userMapper.getUserByUsernameAndPasswordTest(username,password);
    }

    /**
     * 根据用户名和密码得到用户
     * @param username
     * @param password
     * @return
     */
    public User LoginJpa(String username, String password){
        return jpaUserMapper.getByUsernameAndPassword(username,password);
    }

    /**
     * 根据用户名得到用户集合
     * @param username
     * @return
     */
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

    /**
     * 根据用户名，电话，电子邮件得到用户
     * @param username
     * @param phone
     * @param email
     * @return
     */
    public User getUser(String username,String phone,String email){
        return userMapper.getUser(username,phone,email);
    }

    /**
     * 根据用户名得到用户
     * @param username
     * @return
     */
    public User getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    /**
     * 根据电话号码得到用户
     * @param phone
     * @return
     */
    public User getUserPhone(String phone){
        return userMapper.getUserPhone(phone);
    }

    /**
     * mongo保存用户
     * @param user
     */
    public void insert(User user) {
        mongoTemplate.save(user);
    }

}
