package com.bhsoftware.projectserver.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Data
@ToString
@Document(collection = "MongoUser")
public class MongoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field("id")
    private int id;
        @Field("username")
    private String username;
        @Field("password")
    private String password;
        @Field("status")
    private Integer status;
        @Field("email")
    private String email;
        @Field("name")
    private String name;
        @Field("phone")
    private String phone;
        @Field("salt")
    private String salt;

}
