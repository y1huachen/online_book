/**
 * FileName:CategoryDao
 * Author:  y1huachen
 * Date:    2020-06-01 10:52
 * Description:分类数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *(一句话功能简述) <br>
 *(分类数据接口)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

public interface CategoryDao extends JpaRepository<Category, Integer> {

  Category findByName(String name);
}
