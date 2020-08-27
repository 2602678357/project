package com.bhsoftware.projectserver.service;

import com.bhsoftware.projectserver.JPADao.JpaBookDao;
import com.bhsoftware.projectserver.entity.Book;
import com.bhsoftware.projectserver.entity.Menu;
import com.bhsoftware.projectserver.mapper.BookMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书查询service层
 */
@Service
public class BookService {

    //引入图书查询mapper接口
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private JpaBookDao jpaBookDao;

    @Autowired
    private MenuService menuService;

    /**
     * 根据图书标题和种类id查询图书列表
     * @param title
     * @param cid
     * @return
     */
    public List<Book> getListBook(@Param(value="title")String title,@Param(value="cid")Integer cid){
        return bookMapper.getNewListBook(title,cid);
    }

    /**
     * 根据图书种类id和标题得到图书集合
     * @param cid
     * @param title
     * @return
     */
   public List<Book> listMenuAndTile(int cid,String title){
        Menu menu=menuService.get(cid);
        return jpaBookDao.findAllByMenuAndTitle(menu,title);
    }

    /**
     * 根据id降序得到所有图书
     * @return
     */
    public List<Book> list(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return jpaBookDao.findAll(sort);
    }

    /**
     * 根据图书种类id得到所有图书
     * @param cid
     * @return
     */
    public List<Book> listByMenu(int cid){
        Menu menu=menuService.get(cid);
        return jpaBookDao.findAllByMenu(menu);
    }

    /**
     * 添加或修改图书
     * @param book
     */
    public void addOrUpdate(Book book){
        jpaBookDao.save(book);
    }

    /**
     * 根据id删除图书
     * @param id
     */
    public void deleteById(int id){
        jpaBookDao.deleteById(id);
    }

    /**
     * 根据标题或作者模糊查询
     * @param keyword1
     * @param keyword2
     * @return
     */
    public List<Book>  findAllByTitleLikeOrAuthorLike(String keyword1,String keyword2){
        return jpaBookDao.findAllByTitleLikeOrAuthorLike('%'+keyword1+'%','%'+keyword2+'%');
    }

    public Page<Book> getBookListByPage(String title, Pageable pageable){
        return jpaBookDao.getBookListByPage(title,pageable);
    }
}
