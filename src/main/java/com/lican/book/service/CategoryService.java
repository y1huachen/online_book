/**
 * FileName:CategoryService
 * Author:  y1huachen
 * Date:    2020-06-01 11:02
 * Description:分类业务层
 */
package com.lican.book.service;

import com.lican.book.bean.Category;
import com.lican.book.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 *(一句话功能简述) <br>
 *(分类业务层)
 *
 *@author y1huachen
 *@create 2020-06-01
 *@since 1.0.0
 */

@Service
public class CategoryService {

  @Autowired
  private CategoryDao categoryDao;

  public List<Category> findAll() {
    return categoryDao.findAll();
  }

  public Category findById(Integer id) {
    return categoryDao.findById(id).get();
  }

  public boolean add(Category category) {
    Category category1 = findByName(category.getName());
    // 分类不存在才添加
    if(category1==null){
      category.setCreateTime(new Timestamp(System.currentTimeMillis()));
      category.setCountNovels(0);
      categoryDao.save(category);
      return true;
    }
    // 返回false代表分类已存在
    return false;

  }

  // 更新当前分类下小说数量
  public Category update(Category category) {
    Category category1 = categoryDao.getOne(category.getId());
    category1.setCountNovels(category.getCountNovels());
    categoryDao.save(category1);
    return category1;
  }

  public Category findByName(String name) {
    return categoryDao.findByName(name);
  }

  /* 暂时屏蔽删除功能
  public void delete(Integer id) {
    categoryDao.deleteById(id);
  }
  */
}
