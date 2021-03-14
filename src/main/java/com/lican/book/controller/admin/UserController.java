/**
 * FileName:UserController
 * Author:  y1huachen
 * Date:    2020-06-02 10:33
 * Description:用户控制层
 */
package com.lican.book.controller.admin;

import com.lican.book.bean.User;
import com.lican.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(用户控制层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@RestController("AdminUserController")
@RequestMapping("admin")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 查询所有用户
   * @return
   */
  @RequestMapping("user/find/all")
  public List<User> findAllUser() {
    return userService.findAll();
  }

  /**
   * 根据id查询用户
   * @param id
   * @return
   */
  @RequestMapping("user/find/id")
  public User findUserById(int id) {
    return userService.findById(id);
  }

  /**
   * 添加用户
   * @param user
   * @return
   */
  @PostMapping("user/add")
  public User add(User user) {
    if(userService.add(user))
      return userService.findByUsername(user.getUsername());
    return null;
  }
}
