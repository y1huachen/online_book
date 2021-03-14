/**
 * FileName:AdminController
 * Author:  y1huachen
 * Date:    2020-06-01 16:19
 * Description:管理员控制层
 */
package com.lican.book.controller.admin;

import com.lican.book.bean.Admin;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(管理员控制层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@Controller
@RequestMapping("admin")
public class AdminController {

  @Autowired
  private AdminService adminService;

  /**
   * 查询所有管理员
   * @return
   */
  @RequestMapping("admin/find/all")
  @ResponseBody
  public List<Admin> findAllAdmin() {
    return adminService.findAll();
  }

  /**
   * 添加管理员
   * @param admin
   * @return
   */
  @PostMapping("admin/add")
  @ResponseBody
  public ResponseInfo add(Admin admin) {
    if(adminService.add(admin))
      return new ResponseInfo(0,"add admin success");
    return new ResponseInfo(1,"admin name already exists");
  }

  /**
   * 更新管理员密码
   * @param admin
   * @param newPassword
   * @return
   */
  @PostMapping("admin/update/password")
  @ResponseBody
  public ResponseInfo updatePassword(Admin admin, String newPassword) {
    if(adminService.updatePassword(admin,newPassword)==null) {
      return new ResponseInfo(1,"update fail, password error");
    }
    return new ResponseInfo(0,"update success");
  }

  /**
   * 更新管理员头像地址
   * @param admin
   * @return
   */
  @PostMapping("admin/update/avatar_url")
  @ResponseBody
  public Admin updateAvatarUrl(Admin admin) {
    return adminService.updateAvatarUrl(admin);
  }

  /**
   * 根据id查询管理员
   * @param id
   * @return
   */
  @RequestMapping("admin/find/id")
  public String findAdminById(int id, Model model) {
    Admin admin = adminService.findById(id);
    model.addAttribute("admin",admin);
    return "admin/adminInfo";
  }

}
