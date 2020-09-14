package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "admin_permission")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class AdminPermission {

    //权限
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    //名称
    private String name;
    //名称
    @Column(name = "desc_")
    @JsonProperty("desc")
    private String desc;
    //路径
    private String url;
}
