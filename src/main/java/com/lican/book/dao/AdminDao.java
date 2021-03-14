/**
 * FileName:AdminDao
 * Author:  y1huachen
 * Date:    2020-06-01 15:49
 * Description:管理员数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *(一句话功能简述) <br>
 *(管理员数据接口)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

public interface AdminDao extends JpaRepository<Admin, Integer> {

  Admin findByName(String name);

  Admin findByNameAndPassword(String name, String password);
}
