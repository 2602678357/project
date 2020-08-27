package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.Nav;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NavMapper {

    @Select("select * from nav")
    List<Nav> getListNav();

}
