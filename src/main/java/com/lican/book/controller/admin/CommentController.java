/**
 * FileName:CommentController
 * Author:  y1huachen
 * Date:    2020-06-07 22:13
 * Description:留言回复控制层
 */
package com.lican.book.controller.admin;

import com.lican.book.bean.Comment;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(留言回复控制层)
 *
 *@author y1huachen
 *@create 2020-06-07
 *@since 1.0.0
 */

@RestController("AdminCommentController")
@RequestMapping("admin/comment")
public class CommentController {

  @Autowired
  private CommentService commentService;

  @RequestMapping("find/un_reply")
  public List<Comment> findUnReply(){
    return commentService.findUnReply();
  }

  @PostMapping("reply")
  public ResponseInfo reply(Comment comment){
    commentService.reply(comment);
    return new ResponseInfo(0,"reply comment success");
  }
}
