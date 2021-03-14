/**
 * FileName:UserService
 * Author:  y1huachen
 * Date:    2020-06-01 21:30
 * Description:用户业务层
 */
package com.lican.book.service;

import com.lican.book.bean.User;
import com.lican.book.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(用户业务层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  public List<User> findAll() {
    return userDao.findAll();
  }

  public User findById(Integer id) {
    return userDao.findById(id).get();
  }

  public boolean add(User user) {
    User user1 = findByUsername(user.getUsername());
    if(user1==null){
      user.setAvatarUrl("user/default/avatar.jpg");
      user.setIntroduction("这个人还没有介绍自己...");
      userDao.save(user);
      return true;
    }
    return false;
  }

  // 更新用户密码
  public User updatePassword(User user,String newPassword) {
    User user1 = userDao.getOne(user.getId());
    if(user1==null)
      return null;
    if(user1.getPassword()!=null&&user1.getPassword().equals(user.getPassword())){
      // 更新密码
      user1.setPassword(newPassword);
      userDao.save(user1);
      return user1;
    }
    // 更新失败、旧密码不正确
    return null;
  }

  // 更新用户头像地址
  public User updateAvatarUrl(User user) {
    User user1 = userDao.getOne(user.getId());
    if(user1.getAvatarUrl()!=null&&user1.getAvatarUrl().equals(user.getAvatarUrl()))
      return user1;
    user1.setAvatarUrl(user.getAvatarUrl());
    userDao.save(user1);
    return user1;
  }

  // 更新用户简介
  public User updateIntroduction(User user) {
    User user1 = userDao.getOne(user.getId());
    user1.setIntroduction(user.getIntroduction());
    userDao.save(user1);
    return user1;
  }

  public User findByUsername(String name) {
    return userDao.findByUsername(name);
  }

  public User findByUsernameAndPassword(String username,String password){
    User user = userDao.findByUsernameAndPassword(username,password);
    if(user!=null){
      // 去除密码
      user.setPassword("");
    }
    return user;
  }
}
