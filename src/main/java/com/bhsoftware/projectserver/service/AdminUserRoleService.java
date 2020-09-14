package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.JPADao.AdminUserRoleDao;
import com.bhsoftware.projectserver.entity.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    public List<AdminUserRole> listAllByUid(Integer id){
        return adminUserRoleDao.findAllByUid(id);
    }
}
