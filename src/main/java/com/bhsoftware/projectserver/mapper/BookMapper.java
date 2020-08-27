package com.bhsoftware.projectserver.mapper;

import com.bhsoftware.projectserver.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 图书查询mapper映射类
 */
@Mapper
public interface BookMapper {
    /**
     * 根据图书标题查询图书集合
     * @param title 图书标题
     * @return
     */
    @Select({"<script>",
            "select * from book",
            "WHERE 1=1",
            "<when test='title!=null'>",
            "AND title LIKE CONCAT('%',#{title},'%')",
            "</when>",
            "</script>"})
    List<Book> getListBook(@Param(value="title")String title);

    /**
     * 根据标题和种类查询符合条件图书列表
     * @param title 图书标题
     * @param cid 图书种类id
     * @return
     */
//    ("<script>SELECT book.id,book.title,book.abs," +
//            "book.author,book.cover,book.date,book.cid,book.press,menu.name AS mname,menu.id AS menuid " +
//            "FROM book LEFT JOIN menu ON book.cid=menu.id WHERE 1=1 " +
//            "<when test='cid!=null'>  AND menu.id=#{cid} </when> " +
//            "<when test='title!=null'>  AND title LIKE CONCAT('%',#{title},'%')</when>" +
//            "</script>")

    List<Book> getNewListBook(@Param(value="title")String title,@Param(value="cid")Integer cid);


    List<Book> getListBook2();
}
