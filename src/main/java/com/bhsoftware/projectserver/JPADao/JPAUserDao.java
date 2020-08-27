package com.bhsoftware.projectserver.JPADao;

import com.bhsoftware.projectserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPAUserDao extends JpaRepository<User,Integer> {
    User getByUsernameAndPassword(String username,String password);
}
