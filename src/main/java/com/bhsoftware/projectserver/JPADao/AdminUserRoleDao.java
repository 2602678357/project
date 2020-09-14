package com.bhsoftware.projectserver.JPADao;

import com.bhsoftware.projectserver.entity.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AdminUserRoleDao extends JpaRepository<AdminUserRole,Integer> {

    //获取用户所有角色id
    List<AdminUserRole> findAllByUid(Integer uid);
}
