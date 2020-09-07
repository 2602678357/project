package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminRoleMapper {
    /**
     * 根据用户名查找角色信息
     * @param username
     * @return
     */
    @Select(" SELECT\n" +
            "            user.id,\n" +
            "            user.name,\n" +
            "            user.password,\n" +
            "            user.salt,\n" +
            "            user.status,\n" +
            "            user.username,\n" +
            "            admin_role.id,\n" +
            "            admin_role.name,\n" +
            "            admin_role.name_zh,\n" +
            "            admin_role.enabled\n" +
            "        FROM\n" +
            "            user\n" +
            "        RIGHT JOIN admin_user_role ON user.id = admin_user_role.uid\n" +
            "        LEFT JOIN admin_role ON admin_user_role.rid = admin_role.id\n" +
            "        WHERE username=#{username}")
    List<AdminRole> findRoleByUserName(@Param("username") String username);

}
