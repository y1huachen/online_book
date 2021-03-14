/**
 * FileName:CategoryController
 * Author:  y1huachen
 * Date:    2020-06-02 10:32
 * Description:分类控制层
 */
package com.lican.book.controller.admin;

import com.lican.book.bean.Category;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *(一句话功能简述) <br>
 *(分类控制层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@RestController("AdminCategoryController")
@RequestMapping("admin/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  /**
   * 添加分类
   * @param category
   * @return
   */
  @PostMapping("add")
  public ResponseInfo add(Category category) {
    if(categoryService.add(category))
      return new ResponseInfo(0,"add category success");
    return new ResponseInfo(1,"category name already exists");
  }

  /**
   * 通过id获取分类
   * @param id
   * @return
   */
  @RequestMapping("find/id")
  public Category findById(int id) {
    return categoryService.findById(id);
  }

}
