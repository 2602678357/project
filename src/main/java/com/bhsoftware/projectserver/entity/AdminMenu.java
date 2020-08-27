package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
//@Entity
@Table(name = "admin_menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminMenu {
    //菜单id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    //路径
    private String path;
    //名称
    private String name;
    @Column(name = "name_zh")
    @JsonProperty("nameZh")
    private String nameZh;
    //图标
    @Column(name = "icon_cls")
    @JsonProperty("iconCls")
    private String iconCls;
    //vue 前端组件名称
    private String component;
    //父菜单id
    @Column(name = "parent_id")
    @JsonProperty("parentId")
    private Integer parentId;
    //子菜单
    private List<AdminMenu> childAdminMenus;

}
