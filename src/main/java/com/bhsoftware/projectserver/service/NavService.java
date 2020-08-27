package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.entity.Nav;
import com.bhsoftware.projectserver.mapper.NavMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavService {

    @Autowired
    private NavMapper navMapper;

    public List<Nav> getListNav(){
        return navMapper.getListNav();
    }
}
