package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.JPADao.JpaMenuDao;
import com.bhsoftware.projectserver.entity.Menu;
import com.bhsoftware.projectserver.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private JpaMenuDao jpaMenuDao;

    public List<Menu> getMenuList(){
        return menuMapper.getMenuList();
    }

    public List<Menu> list(){
        //Sort sort = new Sort(Sort.Direction.DESC,"id");
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return jpaMenuDao.findAll(sort);
    }
    public Menu get(int id){
        Menu menu = jpaMenuDao.findById(id).orElse(null);
        return menu;
    }
}
