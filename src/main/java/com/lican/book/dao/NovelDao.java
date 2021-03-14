/**
 * FileName:NovelDao
 * Author:  y1huachen
 * Date:    2020-06-02 8:59
 * Description:小说数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(小说数据接口)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

public interface NovelDao extends JpaRepository<Novel, Integer> {

  Novel findByNameAndAuthor(String name, String author);

  @Query(value =  "SELECT * FROM novel WHERE category_id=? AND status=1",nativeQuery = true)
  List<Novel> findByCategory(Integer categoryId);

  @Query(value =  "SELECT * FROM novel WHERE status=1",nativeQuery = true)
  List<Novel> findAll();

  // 分类前十新书
  @Query(value =  "SELECT * FROM novel WHERE category_id=? AND status=1 ORDER BY create_time DESC LIMIT 10",nativeQuery = true)
  List<Novel> findNewByCategory(Integer categoryId);

  // 前十新书
  @Query(value =  "SELECT * FROM novel WHERE status=1 ORDER BY create_time DESC LIMIT 10",nativeQuery = true)
  List<Novel> findNewAll();

}
