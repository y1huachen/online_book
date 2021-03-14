/**
 * FileName:UserDao
 * Author:  y1huachen
 * Date:    2020-06-01 21:21
 * Description:用户数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *(一句话功能简述) <br>
 *(用户数据接口)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

public interface UserDao extends JpaRepository<User, Integer> {

  User findByUsername(String username);

  User findByUsernameAndPassword(String username, String password);
}
