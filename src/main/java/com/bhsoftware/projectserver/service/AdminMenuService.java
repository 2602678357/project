package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.JPADao.AdminMenuDao;
import com.bhsoftware.projectserver.JPADao.AdminRoleMenuDao;
import com.bhsoftware.projectserver.entity.AdminMenu;
import com.bhsoftware.projectserver.entity.AdminRoleMenu;
import com.bhsoftware.projectserver.entity.AdminUserRole;
import com.bhsoftware.projectserver.mapper.AdminMenuMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Autowired
    private AdminMenuDao adminMenuDao;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;

    /**
     * 根据id做父id查询
     * @param id
     * @return
     */
    public List<AdminMenu> getAllByParentId(Integer id){
        return adminMenuDao.findAllByParentId(id);
    }

    public List<AdminMenu> getAdminMenuList(String username){
//        先查所有
        List<AdminMenu> list=adminMenuMapper.getAdminMenuListByName(username);
//        StringUtils.isBlank() 判断某字符串是为空成
        //menuList 存储最终结果
        List<AdminMenu> menuList=new ArrayList<AdminMenu>();
        // 先找到所有的一级菜单
        for(int i=0;i<list.size();i++){
            if((list.get(i).getParentId()==0))
                //将一级菜单添加到最终list
                menuList.add(list.get(i));
        }
        for (AdminMenu menu:menuList){
            menu.setChildAdminMenus(getChildAdminMenu(menu.getId(),list));
        }
        return menuList;
    }

    /**
     * 递归查找子菜单
     * @param id 传入菜单id
     * @param list 传入所有数据
     * @return
     */
    private List<AdminMenu> getChildAdminMenu(Integer id,List<AdminMenu> list){
        //子菜单
        List<AdminMenu> childList=new ArrayList<>();
        for (AdminMenu menu: list) {
            //父id不为0所有子菜单
            if (menu.getParentId() != 0) {
                //如果父id相同，同一父id子菜单加入到list
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
            //遍历子菜单，没有url的菜单肯定是有子菜单的，
            // 有了url的菜单肯定是最终级别分支的菜单，所以对url进行判空决定是否再去查询子菜单
            for (AdminMenu m:
                    childList) {
                if(StringUtils.isBlank(m.getPath())){
                    m.setChildAdminMenus(getChildAdminMenu(m.getId(),list));
                }
            }
            if(childList.size()==0){
                return null;
            }
        return childList;
    }


    /**
     * 调用jpa方法
     */
    public  List<AdminMenu> getMenuByCurrentUser(Integer id){
        //get roles rid
        List<Integer> rids = adminUserRoleService.listAllByUid(id)
             .stream()
             .map(AdminUserRole::getRid)
             .collect(Collectors.toList());
        //获取菜单角色id
        List<Integer> menuIds=adminRoleMenuDao.findAllByRidIn(rids).stream().map(AdminRoleMenu::getMid) .collect(Collectors.toList());
        //获取菜单项
        List<AdminMenu> menus=adminMenuDao.findAllByIdIn(menuIds).stream().distinct().collect(Collectors.toList());
        handleMenus(menus);
        return menus;
    }

    /**
     * 生成菜单框架
     */
    public void handleMenus(List<AdminMenu> menus){
        menus.forEach(m->{
            List<AdminMenu> children=getAllByParentId(m.getId());
            m.setChildAdminMenus(children);
        });
        menus.removeIf(m ->m.getParentId()!=0);
    }

}
