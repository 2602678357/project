package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.entity.AdminMenu;
import com.bhsoftware.projectserver.mapper.AdminMenuMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

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

}
