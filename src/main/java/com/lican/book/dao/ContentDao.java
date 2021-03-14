/**
 * FileName:ContentDao
 * Author:  y1huachen
 * Date:    2020-06-02 15:57
 * Description:内容数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *(一句话功能简述) <br>
 *(内容数据接口)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

public interface ContentDao extends JpaRepository<Content, Integer> {

  @Query(value =  "SELECT * FROM content WHERE chapter_id=?",nativeQuery = true)
  Content findByChapter(Integer chapterId);

}
