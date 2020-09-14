package com.bhsoftware.projectserver.JPADao;

import com.bhsoftware.projectserver.entity.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AdminMenuDao extends JpaRepository<AdminMenu,Integer> {
    //获取菜单项
    List<AdminMenu> findAllByIdIn(List<Integer> id);


    List<AdminMenu> findAllByParentId(Integer parentId);

}
