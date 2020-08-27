package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.User;
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
}
