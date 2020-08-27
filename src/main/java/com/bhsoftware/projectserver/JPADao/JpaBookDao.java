package com.bhsoftware.projectserver.JPADao;

import com.bhsoftware.projectserver.entity.Book;
import com.bhsoftware.projectserver.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface JpaBookDao extends JpaRepository<Book,Integer> , JpaSpecificationExecutor<Book> {

    /**
     * 查询所有图书根据标题和图书种类id
     * @param title
     * @param cid
     * @return
     */
    List<Book> findAllByMenuAndTitle(Menu menu,String title);

    /**
     * 查询所有图书根据图书种类
     * @param menu
     * @return
     */
    List<Book>  findAllByMenu(Menu menu);

    List<Book> findAllByTitleLikeOrAuthorLike(String title,String
            author);

    /**
     * 传入标题
     * @param title
     * @param pageable
     * @return
     */
    @Query(value="SELECT book.id,book.title,book.abs,book.author,book.cover," +
            "book.date,book.cid,book.press,menu.name,menu.id FROM book  " +
            "LEFT JOIN menu  ON book.cid=menu.id WHERE 1=1 AND (title LIKE CONCAT('%',:title,'%') OR :title IS NULL )", nativeQuery = true)
    Page<Book> getBookListByPage(String title, Pageable pageable);
}
