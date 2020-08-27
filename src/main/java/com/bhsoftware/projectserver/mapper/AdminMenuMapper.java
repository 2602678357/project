package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.AdminMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMenuMapper {

    @Select("select * from admin_menu")
    List<AdminMenu> getAdminMenuList();

    /**
     * 根据用户名查菜单
     * @return
     */
    @Select("SELECT DISTINCT component,name_zh,path,parent_id,admin_role_menu.mid AS id,admin_role_menu.rid FROM admin_menu LEFT JOIN admin_role_menu ON \n" +
            "admin_menu.id=admin_role_menu.mid WHERE admin_role_menu.rid = ANY (SELECT nameRole.id FROM (SELECT admin_role.id,user.username FROM USER LEFT JOIN  \n" +
            "admin_user_role ON user.id=admin_user_role.uid \n" +
            "LEFT JOIN admin_role ON admin_user_role.rid=admin_role.id)nameRole WHERE nameRole.username=#{username})")
    List<AdminMenu> getAdminMenuListByName(@Param(value="username")String username);
}
