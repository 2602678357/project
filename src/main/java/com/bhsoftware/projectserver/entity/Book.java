package com.bhsoftware.projectserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * 图书实体类
 */
@Data
@ToString
@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Book {
	//图书id
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
	private Integer id;
	//图书图片地址
	private String cover;
	//图书标题
	private String title;
	//图书标题
	private String author;
	//图书日期
	private String date;
	//图书出版社
	private String press;
	//图书简介
	private String abs;

	@ManyToOne
	@JoinColumn(name="cid",referencedColumnName = "id")
	private Menu menu;
}