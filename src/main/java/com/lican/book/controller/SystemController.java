/**
 * FileName:SystemController
 * Author:  y1huachen
 * Date:    2020-06-01 22:19
 * Description:系统访问入口控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.Admin;
import com.lican.book.bean.User;
import com.lican.book.service.AdminService;
import com.lican.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *(一句话功能简述) <br>
 *(系统访问入口控制层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@Controller
public class SystemController {

  @Autowired
  private AdminService adminService;
  @Autowired
  private UserService userService;

  /**
   * 管理员登录
   * @param name
   * @param password
   * @return
   */
  @PostMapping("login/admin")
  public String loginAdmin(String name, String password, Model model,
                           HttpServletRequest request){
    Admin admin = adminService.findByNameAndPassword(name,password);
    if(admin == null){
      model.addAttribute("msg","error name or password");
      return "adminLogin";
    }
    // 这里将管理员信息加入session
    HttpSession session = request.getSession();
    session.setAttribute("admin",admin);
    return "redirect:/admin/admin";
  }

  /**
   * 管理员登出
   * @return
   */
  @RequestMapping("logout/admin")
  public String logoutAdmin(HttpServletRequest request){
    // 这里将管理员信息从session移除
    HttpSession session = request.getSession();
    session.removeAttribute("admin");
    return "redirect:/login/admin?msg=please login again";
  }


  /**
   * 用户注册
   * @param user
   * @return
   */
  @PostMapping("register/user")
  public String registerUser(User user, Model model){
    if(userService.add(user)){
      // 注册成功跳转到登录页面
      return "redirect:/login/user?msg=hello,"+user.getUsername()+" please login";
    }
    // 这里返回注册时用户名错冲突
    model.addAttribute("msg", "username exists already");
    return "register";
  }

  /**
   * 用户登录
   * @param name
   * @param password
   * @return
   */
  @PostMapping("login/user")
  public String loginUser(String name, String password, Model model,
                          HttpServletRequest request){
    User user = userService.findByUsernameAndPassword(name,password);
    if(user == null){
      model.addAttribute("msg","error name or password");
      return "userLogin";
    }
    // 这里将用户信息加入session
    HttpSession session = request.getSession();
    session.setAttribute("user",user);
    return "redirect:/index";
  }

  /**
   * 用户登出
   * @return
   */
  @RequestMapping("logout/user")
  public String logoutUser(HttpServletRequest request){
    // 这里将用户信息从session移除
    HttpSession session = request.getSession();
    session.removeAttribute("user");
    return "redirect:/login/user?msg=please login again";
  }

  /**
   * 首页（后续添加新书榜、人气榜、字数榜）
   * @return
   */
  @RequestMapping("index")
  public String index(){
    return "index";
  }

  @RequestMapping("read")
  public String read(){
    return "read";
  }

  @RequestMapping("admin/admin")
  public String adminManage(){
    return "admin/admin";
  }

  @RequestMapping("admin/user")
  public String userManage(){
    return "admin/user";
  }

  @RequestMapping("admin/novel")
  public String novelManage(){
    return "admin/novel";
  }

  @RequestMapping("admin/comment")
  public String commentManage(){
    return "admin/comment";
  }

  @RequestMapping("login/admin")
  public String adminLogin(String msg, Model model){
    if(msg!=null&&!"".equals(msg)){
      model.addAttribute("msg", msg);
    }
    return "adminLogin";
  }

  @RequestMapping("login/user")
  public String userLogin(String msg, Model model){
    if(msg!=null&&!"".equals(msg)){
      model.addAttribute("msg", msg);
    }
    return "userLogin";
  }

  @RequestMapping("register")
  public String register(){
    return "register";
  }

}
