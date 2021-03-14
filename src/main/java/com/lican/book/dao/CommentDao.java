/**
 * FileName:CommentDao
 * Author:  y1huachen
 * Date:    2020-06-07 21:45
 * Description:留言数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.Comment;
import com.lican.book.bean.Novel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(留言数据接口)
 *
 *@author y1huachen
 *@create 2020-06-07
 *@since 1.0.0
 */

public interface CommentDao extends JpaRepository<Comment, Integer> {

  @Query(value =  "SELECT * FROM comment WHERE is_reply=0",nativeQuery = true)
  List<Comment> findUnReplyComments();

  @Query(value =  "SELECT * FROM comment WHERE novel_id=?",nativeQuery = true)
  List<Comment> findByNovel(Integer novelId);
}
