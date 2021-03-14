/**
 * FileName:CommentService
 * Author:  y1huachen
 * Date:    2020-06-07 21:57
 * Description:留言业务层
 */
package com.lican.book.service;

import com.lican.book.bean.Category;
import com.lican.book.bean.Comment;
import com.lican.book.bean.Novel;
import com.lican.book.dao.CommentDao;
import com.lican.book.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 *(一句话功能简述) <br>
 *(留言业务层)
 *
 *@author y1huachen
 *@create 2020-06-07
 *@since 1.0.0
 */

@Service
public class CommentService {

  @Autowired
  private CommentDao commentDao;
  @Autowired
  private NovelService novelService;

  public List<Comment> findAll() {
    return commentDao.findAll();
  }

  public List<Comment> findUnReply() {
    return commentDao.findUnReplyComments();
  }

  public List<Comment> findByNovel(Integer novelId){
    return commentDao.findByNovel(novelId);
  }

  public Comment findById(Integer id) {
    return commentDao.findById(id).get();
  }

  public void add(Comment comment) {
    comment.setReplyContent("");
    comment.setIsReply(0);
    comment.setCreateTime(new Timestamp(System.currentTimeMillis()));
    commentDao.save(comment);
    // 更新小说评论数
    Novel novel = comment.getNovel();
    novel.setCountComments(novel.getCountComments()+1);
    novelService.updateCountComments(novel);
  }

  // 回复留言
  public void reply(Comment comment) {
    Comment comment1 = findById(comment.getId());
    comment1.setReplyContent(comment.getReplyContent());
    comment1.setIsReply(comment.getIsReply());
    comment1.setReplyTime(new Timestamp(System.currentTimeMillis()));
    comment1.setAdmin(comment.getAdmin());
    commentDao.save(comment1);
  }

}
