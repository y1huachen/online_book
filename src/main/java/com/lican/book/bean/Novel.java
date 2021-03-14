/**
 * FileName:Novel
 * Author:  y1huachen
 * Date:    2020-05-31 14:59
 * Description:小说
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *(一句话功能简述) <br>
 *(小说)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */
@Entity
@Table(name = "novel")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Novel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 书籍ID
  @Column(length = 50)
  private String name; // 书名
  @Column(length = 40)
  private String author; // 作者
  @Column(length = 1000)
  private String introduction; // 书籍简介
  @Column(length = 200)
  private String coverUrl; // 封面地址
  private Integer totalWords; // 总字数
  private Integer countComments; // 评论数
  private Timestamp createTime; // 发布时间
  private Timestamp expireTime; // 过期时间
  private Integer views; // 已看人数
  private Integer status; // 状态(0删除,1存在)

  @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH},optional = false)
  @JoinColumn(name = "category_id")
  private Category category; // 所属分类

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public Integer getTotalWords() {
    return totalWords;
  }

  public void setTotalWords(Integer totalWords) {
    this.totalWords = totalWords;
  }

  public Integer getCountComments() {
    return countComments;
  }

  public void setCountComments(Integer countComments) {
    this.countComments = countComments;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Timestamp getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(Timestamp expireTime) {
    this.expireTime = expireTime;
  }

  public Integer getViews() {
    return views;
  }

  public void setViews(Integer views) {
    this.views = views;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
