package com.bhsoftware.projectserver.Service;

import com.bhsoftware.projectserver.JPADao.JpaBookDao;
import com.bhsoftware.projectserver.entity.Book;
import com.bhsoftware.projectserver.entity.Menu;
import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.mapper.BookMapper;
import com.bhsoftware.projectserver.mapper.UserMapper;
import com.bhsoftware.projectserver.service.BookService;
import com.bhsoftware.projectserver.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookTest {

    @Autowired
    private JpaBookDao jpaBookDao;

    @Autowired
    private BookService bookService;


    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        Menu menu=new Menu();
        menu.setId(1);
        String title="三体";
        List<Book> list=jpaBookDao.findAllByMenuAndTitle(menu,title);
       for (Book book:
             list) {
            System.out.println(book);
        }
    }
    @Test
    public void test2(){
        Menu menu=new Menu();
        menu.setId(1);
        List<Book> list=jpaBookDao.findAllByMenu(menu);
        for (Book book:
             list) {
            System.out.println(book);
        }
    }

    @Test
    public void test3(){
        List<Book> list=bookService.listByMenu(1);
        for (Book book:
             list) {
            System.out.println(book);
        }
    }

    @Test
    public void test6(){
        String k1="三体";
        //String k2="刘慈欣";
        List<Book> list=bookService.findAllByTitleLikeOrAuthorLike(k1,k1);
        for (Book book:
             list) {
            System.out.println(book);
        }
    }


    @Test
    public void test4(){
        List<Book> list=bookMapper.getListBook2();
        for (Book book:
             list) {
            System.out.println(book);
        }
    }


    @Test
    public void test5(){
        String username="admin";
        String password="lishuai123";
        User user= userMapper.getUserByUsernameAndPasswordTest(username,password);
        System.out.println(user.toString());
    }

}
