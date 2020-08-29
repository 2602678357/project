package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} AND password=#{password}")
    User getUserByUsernameAndPassword(String username,String password);

    User getUserByUsernameAndPasswordTest(String username,String password);

    @Select({"<script>",
            "select * from user",
            "WHERE 1=1",
            "<when test='username!=null'>",
            "AND username LIKE CONCAT('%',#{username},'%')",
            "</when>",
            "</script>"})
    List<User> getListUser(@Param(value="username")String username);


    @Insert("insert into user(username, password,email,phone,name,salt) values(#{username}, #{password},#{email}, #{phone}, #{name},#{salt})")
    void insertUser(@Param(value="username")String username,
                    @Param(value="password")String password,
                    @Param(value="email")String email,
                    @Param(value="phone")String phone,
                    @Param(value="name")String name,
                    @Param(value="salt")String salt);


    @Select("select username,email,phone from user where username = #{username} AND email = #{email} AND phone = #{phone}")
    User getUser(@Param(value = "username") String username,@Param(value = "email") String email,@Param(value = "phone") String phone);

    @Select("select phone from user where  phone = #{phone}")
    User getUserPhone(@Param(value = "phone") String phone);

    @Select("select username from user where  phone = #{username}")
    User getUserByName(@Param(value = "username") String username);

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User selectByUserName(@Param("username") String username);
}
