package com.bhsoftware.projectserver.JPADao;

import com.bhsoftware.projectserver.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMenuDao extends JpaRepository<Menu,Integer> {
}
