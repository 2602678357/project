package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper {

    @Select("select * from menu")
    List<Menu> getMenuList();
}
