/**
 * FileName:CommentController
 * Author:  y1huachen
 * Date:    2020-06-07 22:20
 * Description:留言控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.Comment;
import com.lican.book.bean.Novel;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(留言控制层)
 *
 *@author y1huachen
 *@create 2020-06-07
 *@since 1.0.0
 */
@RestController("CommentController")
@RequestMapping("comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @PostMapping("add")
  public ResponseInfo add(Comment comment) {
    commentService.add(comment);
    return new ResponseInfo(0,"add comment success");
  }

  @RequestMapping("find/novel")
  public List<Comment> findByNovel(Integer novelId){
    return commentService.findByNovel(novelId);
  }
}
