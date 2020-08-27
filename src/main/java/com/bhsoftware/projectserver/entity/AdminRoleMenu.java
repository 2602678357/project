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
@Table(name = "admin_role_menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminRoleMenu {
    //菜单id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    //权限id
    private Integer rid;

    //菜单id
    private Integer mid;

}
