package com.bhsoftware.projectserver.JPADao;

import com.bhsoftware.projectserver.entity.AdminRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AdminRoleMenuDao extends JpaRepository<AdminRoleMenu,Integer> {


    //获取菜单角色id
    List<AdminRoleMenu> findAllByRidIn(List<Integer> rids);
}
