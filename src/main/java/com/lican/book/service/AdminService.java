/**
 * FileName:AdminService
 * Author:  y1huachen
 * Date:    2020-06-01 15:56
 * Description:管理员业务层
 */
package com.lican.book.service;

import com.lican.book.bean.Admin;
import com.lican.book.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(管理员业务层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@Service
public class AdminService {

  @Autowired
  private AdminDao adminDao;

  public List<Admin> findAll() {
    return adminDao.findAll();
  }

  public Admin findById(Integer id) {
    return adminDao.findById(id).get();
  }

  public boolean add(Admin admin) {
    Admin admin1 = findByName(admin.getName());
    if(admin1==null){
      admin.setAvatarUrl("admin/default/avatar.jpg");
      adminDao.save(admin);
      return true;
    }
    return false;
  }

  // 更新管理员密码
  public Admin updatePassword(Admin admin,String newPassword) {
    Admin admin1 = adminDao.getOne(admin.getId());
    if(admin1==null)
      return null;
    if(admin1.getPassword()!=null&&admin1.getPassword().equals(admin.getPassword())){
      // 更新密码
      admin1.setPassword(newPassword);
      adminDao.save(admin1);
      return admin1;
    }
    // 更新失败、旧密码不正确
    return null;
  }

  // 更新管理员头像地址
  public Admin updateAvatarUrl(Admin admin) {
    Admin admin1 = adminDao.getOne(admin.getId());
    admin1.setAvatarUrl(admin.getAvatarUrl());
    adminDao.save(admin1);
    return admin1;
  }

  public Admin findByName(String name) {
    return adminDao.findByName(name);
  }

  public Admin findByNameAndPassword(String name,String password){
    Admin admin = adminDao.findByNameAndPassword(name,password);
    if(admin!=null){
      // 去除密码
      admin.setPassword("");
    }
    return admin;
  }
}
