/**
 * FileName:UserController
 * Author:  y1huachen
 * Date:    2020-06-01 21:48
 * Description:用户控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.ResponseInfo;
import com.lican.book.bean.User;
import com.lican.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *(一句话功能简述) <br>
 *(用户控制层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@Controller("UserController")
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;

  /**
   * 更新用户密码
   * @param user
   * @param newPassword
   * @return
   */
  @PostMapping("update/password")
  @ResponseBody
  public ResponseInfo updatePassword(User user, String newPassword) {
    if(userService.updatePassword(user,newPassword)==null){
      return new ResponseInfo(1,"update fail, password error");
    }
    return new ResponseInfo(0,"update success");
  }

  /**
   * 更新用户头像路径
   * @param user
   * @return
   */
  @PostMapping("update/avatar_url")
  @ResponseBody
  public User updateAvatarUrl(User user) {
    return userService.updateAvatarUrl(user);
  }

  /**
   * 更新用户简介
   * @param user
   * @return
   */
  @PostMapping("update/introduction")
  @ResponseBody
  public ResponseInfo updateIntroduction(User user) {
    userService.updateIntroduction(user);
    return new ResponseInfo(0,"update success");
  }

  @RequestMapping("info")
  public String findById() {
    return "userInfo";
  }
}
