package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "admin_role_permission")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminRolePermission {

    //菜单id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    //角色id
    private Integer rid;

    //权限id
    private Integer pid;

}
