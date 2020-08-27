package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 导航栏实体类
 */
@Data
@ToString
@Entity
@Table(name = "nav")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Nav {
    //导航栏id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    //导航栏路径名
    private String name;
    //导航栏名称
    private String navItem;
}
