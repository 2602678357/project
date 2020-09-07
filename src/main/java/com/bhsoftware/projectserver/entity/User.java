package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户实体类
 */
@Data
@ToString
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
//    @Field("id")
    private int id;
//    @Field("username")
    private String username;
//    @Field("password")
    private String password;
//    @Field("status")
    private Integer status;
//    @Field("email")
    private String email;
//    @Field("name")
    private String name;
//    @Field("phone")
    private String phone;
//    @Field("salt")
    private String salt;

}
