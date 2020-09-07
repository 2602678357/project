package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 角色实体类
 */
@Data
@ToString
@Entity
@Table(name = "admin_role")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    private String name;
    
    @Column(name = "name_zh")
    @JsonProperty("nameZh")
    private String nameZh;
    
    private Integer enabled;

}