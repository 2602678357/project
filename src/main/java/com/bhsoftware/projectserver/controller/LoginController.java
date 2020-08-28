package com.bhsoftware.projectserver.controller;

import com.bhsoftware.projectserver.JPADao.JpaBookDao;
import com.bhsoftware.projectserver.entity.*;
import com.bhsoftware.projectserver.result.Result;
import com.bhsoftware.projectserver.service.*;
import com.bhsoftware.projectserver.utils.Response;
import com.bhsoftware.projectserver.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@Api(description = "后台接口")
@Controller
public class LoginController {
//    @PostMapping(value = "/api/login")
//    @ResponseBody
//    public Result login(@RequestBody User requestUser){
//        String username=requestUser.getUsername();
//        username= HtmlUtils.htmlEscape(username);
//        if(!Objects.equals("admin",username)||!Objects.equals("123456",requestUser.getPassword())){
//            String message="账号密码错误";
//            System.out.println(message);
//            return new Result(400);
//        }else{
//            System.out.println(username);
//            System.out.println("访问成功 ");
//            return new Result(200);
//
//        }
//    }

    @Autowired
    private UserService userService;

    @Autowired
    private NavService navService;

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private BookService bookService;

    @Autowired
    private JpaBookDao jpaBookDao;

//    @PostMapping(value = "/api/login")
//    @ResponseBody
//    public Result login(@RequestBody User requestUser){
//        String username=requestUser.getUsername();
//        String password=requestUser.getPassword();
//        System.out.println(username);
//        User user=userService.LoginUserTest(username,password);
//        if(user!=null){
//            return new Result(200);
//        }
//        else{
//            return new Result(400);
//        }
//    }

    //    @PostMapping(value = "/api/login")
//    @ResponseBody
//    public Result login(@RequestBody User requestUser){
//        String username=requestUser.getUsername();
//        String password=requestUser.getPassword();
//        System.out.println(username);
//        User user=userService.LoginUser(username,password);
//        if(user!=null){
//            return new Result(200);
//        }
//        else{
//            return new Result(400);
//        }
//    }
    @ApiOperation(value = "用戶登录", notes = "用户登录")
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession session) {

        String username = requestUser.getUsername();
        String password = requestUser.getPassword();
        User user = userService.LoginJpa(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return new Result(200);
        } else {
            return new Result(400);
        }
    }

    @ApiOperation(value = "返回所有用户", notes = "返回所有用户")
    @GetMapping("/api/find")
    @ResponseBody
    public Response<PageInfo<User>> paging(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize,
                                           @RequestParam(value = "username", required = false) String username) {

        System.out.println(pageNo);
        PageHelper.startPage(pageNo, pageSize);
        List<User> list = userService.getListUser(username);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        System.out.println(Response.yes(pageInfo));
        return Response.yes(pageInfo);
    }

    /**
     * @param pageNo   当前页
     * @param pageSize 每页大小
     * @param title    前端传入参数图书标题
     * @param cid      前端传入参数图书分类id
     * @return
     */
    @ApiOperation(value = "mybatis返回所有图书", notes = "mybatis返回所有图书")
    @GetMapping("/api/book")
    @ResponseBody
    public Response<PageInfo<Book>> Bookpaging(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize,
                                               @RequestParam(value = "title", required = false) String title,
                                               @RequestParam(value = "cid", required = false) Integer cid) {

        PageHelper.startPage(pageNo, pageSize);
        List<Book> list = bookService.getListBook(title, cid);
        PageInfo<Book> pageInfo = new PageInfo<>(list);
//        System.out.println(Response.yes(pageInfo));
        return Response.yes(pageInfo);
    }

    @ApiOperation(value = "返回所有导航栏", notes = "返回所有导航栏")
    @GetMapping("/api/nav")
    @ResponseBody
    public Response<PageInfo<Nav>> Navpaging() {
        List<Nav> list = navService.getListNav();
        PageInfo<Nav> pageInfo = new PageInfo<>(list);
        return Response.yes(pageInfo);
    }

    @ApiOperation(value = "返回侧边栏", notes = "返回侧边栏")
    @GetMapping("/api/menu")
    @ResponseBody
    public Response<PageInfo<Menu>> Menupaging() {
        List<Menu> list = menuService.getMenuList();
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        return Response.yes(pageInfo);
    }

    @ApiOperation(value = "JPA返回所有菜单", notes = "JPA返回所有菜单")
    @GetMapping("/api/AdminMenu")
    @ResponseBody
    public Response<List<AdminMenu>> AdminMenuPaging() {
        String username = "admin";
        List<AdminMenu> list = adminMenuService.getAdminMenuList(username);
        return Response.yes(list);
    }

    //@RequestParam(value = "keywords", required = false) String keywords,
    //@RequestParam(value = "cid", required = false) Integer cid
//    @ApiOperation(value = "分页返回所有图书" ,  notes="分页返回所有图书")
//    @GetMapping("/api/books")
//    @ResponseBody
//    public Map<String,Object> list(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
//                                   @RequestParam(value = "pagesize", required = false, defaultValue = "8") Integer pagesize,
//                                   @RequestParam(value = "cid", required = false) Integer cid,
//                                   @RequestParam(value = "keywords", required = false) String keywords,
//                                   ){
//        //return bookService.list();
//        PageRequest pageRequest=PageRequest.of(currentPage-1,pagesize);
//        Page<Book> pagination=jpaBookDao.findAll(pageRequest);
//        Map<String,Object> map=new HashMap<>();
//        map.put("pagination",pagination);
//        map.put("currentPage",currentPage);
//        map.put("pagesize",pagesize);
//        return map;
//    }


    @ApiOperation(value = "JPA分页查询返回所有图书", notes = "JPA分页查询返回所有图书")
    @GetMapping("/api/Pagebooks")
    @ResponseBody
    public Map<String, Object> getBookListByPage(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                                 @RequestParam(value = "pagesize", required = false, defaultValue = "8") Integer pagesize,
                                                 @RequestParam(value = "keywords", required = false) String keywords
    ) {
        System.out.println(keywords);
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pagesize);
        Map<String, Object> map = new HashMap<>();
        if (keywords == null) {
            Page<Book> page = jpaBookDao.findAll(pageRequest);
            map.put("page", page);
        } else {
            Page<Book> page = bookService.getBookListByPage(keywords, pageRequest);
            map.put("page", page);
        }
        map.put("currentPage", currentPage);
        map.put("pagesize", pagesize);
        return map;
    }

    @ApiOperation(value = "查询所有分类下图书", notes = "查询所有分类下图书")
    @GetMapping("/api/categories/{cid}/books")
    @ResponseBody
    public List<Book> listByMenu(@PathVariable("cid") int cid) {
        if (cid != 0 && cid != 7) {
            return bookService.listByMenu(cid);
        } else if (cid == 7) {
            return bookService.list();
        }
        return bookService.list();
    }

    @ApiOperation(value = "根据标题模糊搜索得到分类图书下图书", notes = "根据标题模糊搜索得到分类图书下图书")
    @ResponseBody
    @GetMapping("/api/menu/{cid}/{title}")
    public List<Book> listByMenuAndTitle(@PathVariable("cid") int cid, @RequestParam String title) {
        List<Book> list = bookService.listMenuAndTile(cid, title);
        return list;
    }

    /**
     * 新增或修改图书
     *
     * @param book
     * @return
     */
    @ApiOperation(value = "新增修改图书", notes = "新增修改图书")
    @PostMapping("/api/book")
    @ResponseBody
    public Book addOrUpdate(@RequestBody Book book) {
        System.out.println(book);
        bookService.addOrUpdate(book);
        return book;
    }

    /**
     * 删除图书
     *
     * @param book
     */
    @ApiOperation(value = "删除图书", notes = "删除图书")
    @PostMapping("/api/delete")
    @ResponseBody
    public void delete(@RequestBody Book book) {
        bookService.deleteById(book.getId());
    }

    /**
     * 根据标题或作者实现模糊查询
     */

    @ApiOperation(value = "根据标题或作者模糊查询", notes = "根据标题或作者模糊查询")
    @GetMapping("/api/search")
    @ResponseBody
    public List<Book> findAllByTitleLikeOrAuthorLike(@RequestParam String keywords) throws IOException {
        System.out.println(keywords);
        if (!(keywords.equals(""))) {
            return bookService.findAllByTitleLikeOrAuthorLike(keywords, keywords);
        }
        return bookService.list();

    }

    @ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/api/covers")
    @ResponseBody
    public String fileUpload(MultipartFile file) {
        String folder = "d:/vue/img1";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomFileName() + file.getOriginalFilename().
                substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8090/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }



    @ApiOperation(value = "增加用户", notes = "增加用户")
    @PostMapping("/api/addUser")
    @ResponseBody
    public Result addUser(@RequestBody User requestUser){
        String username=requestUser.getUsername();
        String password=requestUser.getPassword();
        String phone=requestUser.getPhone();
        String realname=requestUser.getRealname();
        String email=requestUser.getEmail();
        User user=userService.getUser(username,phone,email);
        if(user==null){
            userService.addUser(username,password,email,phone,realname);
            return new Result(200);
        }
        return new Result(400);
    }

    @ApiOperation(value = "校验用户", notes = "校验用户")
    @GetMapping("/api/checkUserPhone")
    @ResponseBody
    public Result checkUser(@RequestParam String phone){
        System.out.println(phone);
        User user=userService.getUserPhone(phone);
        if(user==null){
            return new Result(200);
        }
        return new Result(400);
    }
}

