package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 权限菜单实体类
 */
@Entity
@Table(name = "admin_menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminMenu  implements Serializable {
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

    private String perms;
    //子菜单
    @Transient
    private List<AdminMenu> childAdminMenus;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public List<AdminMenu> getChildAdminMenus() {
        return childAdminMenus;
    }

    public void setChildAdminMenus(List<AdminMenu> childAdminMenus) {
        this.childAdminMenus = childAdminMenus;
    }
}
