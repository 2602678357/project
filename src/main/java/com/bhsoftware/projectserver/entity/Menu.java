package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 图书种类实体类表代替Category
 */
@Data
@ToString
@Entity
@Table(name = "menu")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Menu {
    //图书种类id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    //图书名称
    private String name;
}
