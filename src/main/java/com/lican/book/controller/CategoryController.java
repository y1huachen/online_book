/**
 * FileName:CategoryController
 * Author:  y1huachen
 * Date:    2020-06-01 11:11
 * Description:分类控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.Category;
import com.lican.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(分类控制层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@RestController("CategoryController")
@RequestMapping("category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  /**
   * 查询所有分类
   * @return
   */
  @RequestMapping("find/all")
  public List<Category> findAll() {
    return categoryService.findAll();
  }

}
