package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.AdminMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMenuMapper {

    /**
     * 得到所有菜单
     * @return
     */
    @Select("select * from admin_menu")
    List<AdminMenu> getAdminMenuList();

    /**
     * 根据角色id查找角色id对应权限
     * @param id
     * @return
     */
    @Select("SELECT\n" +
            "                    admin_role.id,\n" +
            "                        admin_role.name,\n" +
            "                        admin_role.name_zh,\n" +
            "                       admin_role.enabled,\n" +
            "                        admin_menu.id,\n" +
            "                        admin_menu.perms,\n" +
            "                        admin_menu.name,\n" +
            "                       admin_menu.name_zh,\n" +
            "                        admin_menu.parent_id,\n" +
            "                        admin_menu.path,\n" +
            "                       admin_menu.icon_cls,\n" +
            "                      admin_menu.component\n" +
            "                   FROM\n" +
            "                     (admin_menu, admin_role)\n" +
            "                  RIGHT JOIN admin_role_menu ON admin_menu.id = admin_role_menu.mid\n" +
            "                   AND admin_role_menu.rid= admin_role.id\n" +
            "                    WHERE admin_role.id=#{id}")
    List<AdminMenu> findAdminMenuByRoleId(@Param("id") Integer id);


    /**
     * 根据用户名查菜单
     * @return
     */
    @Select("SELECT name_zh,name,component,path,parent_id,admin_role_menu.mid AS id,admin_role_menu.rid FROM admin_menu LEFT JOIN admin_role_menu ON \n" +
            "admin_menu.id=admin_role_menu.mid WHERE admin_role_menu.rid = ANY (SELECT nameRole.id FROM (SELECT admin_role.id,user.username FROM user LEFT JOIN  \n" +
            "admin_user_role ON user.id=admin_user_role.uid \n" +
            "LEFT JOIN admin_role ON admin_user_role.rid=admin_role.id)nameRole WHERE nameRole.username=#{username})GROUP BY name_zh")
    List<AdminMenu> getAdminMenuListByName(@Param(value="username")String username);
}
