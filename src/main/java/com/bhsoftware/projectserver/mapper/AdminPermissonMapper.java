package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.AdminPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminPermissonMapper {



    @Select("SELECT                  admin_role.id,\n" +
            "                                   admin_role.name,\n" +
            "                                    admin_role.name_zh,\n" +
            "                                  admin_role.enabled,\n" +
            "                                    admin_permission.id,\n" +
            "                                   admin_permission.name,\n" +
            "                                   admin_permission.desc_,\n" +
            "                                   admin_permission.url\n" +
            "                             FROM\n" +
            "                               (admin_permission, admin_role)\n" +
            "                             RIGHT JOIN admin_role_permission ON admin_permission.id = admin_role_permission.pid\n" +
            "                              AND admin_role_permission.rid= admin_role.id\n" +
            "                              WHERE admin_role.id=#{id}")
    List<AdminPermission> findAdminPermissionByRoleId(@Param("id") Integer id);
}
