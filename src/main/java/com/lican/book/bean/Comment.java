/**
 * FileName:Comment
 * Author:  y1huachen
 * Date:    2020-05-31 22:22
 * Description:留言
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *(一句话功能简述) <br>
 *(留言)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */
@Entity
@Table(name = "comment")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 留言ID
  @Column(length = 1000)
  private String content; // 留言内容
  @Column(length = 1000)
  private String replyContent; // 回复内容
  private Integer isReply; // 是否回复 0未回复 1回复 2无需回复
  private Timestamp createTime; // 留言时间
  private Timestamp replyTime; // 回复时间

  @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH},optional = false)
  @JoinColumn(name = "novel_id")
  private Novel novel; // 所属小说
  @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH},optional = false)
  @JoinColumn(name = "user_id")
  private User user; // 所属用户
  @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH})
  @JoinColumn(name = "admin_id")
  private Admin admin; // 回复所属管理员

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getReplyContent() {
    return replyContent;
  }

  public void setReplyContent(String replyContent) {
    this.replyContent = replyContent;
  }

  public Integer getIsReply() {
    return isReply;
  }

  public void setIsReply(Integer isReply) {
    this.isReply = isReply;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getReplyTime() {
    return replyTime;
  }

  public void setReplyTime(Timestamp replyTime) {
    this.replyTime = replyTime;
  }

  public Novel getNovel() {
    return novel;
  }

  public void setNovel(Novel novel) {
    this.novel = novel;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Admin getAdmin() {
    return admin;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }
}
