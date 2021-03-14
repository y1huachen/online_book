/**
 * FileName:Content
 * Author:  y1huachen
 * Date:    2020-05-31 22:02
 * Description:章节内容
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *(一句话功能简述) <br>
 *(章节内容)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */
@Entity
@Table(name = "content")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Content {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 章节内容ID
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(columnDefinition = "text")
  private String content; // 章节内容
  private Integer words; // 章节字数

  @OneToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH},optional = false)
  @JoinColumn(name = "chapter_id")
  private Chapter chapter; // 所属章节

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

  public Integer getWords() {
    return words;
  }

  public void setWords(Integer words) {
    this.words = words;
  }

  public Chapter getChapter() {
    return chapter;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }
}
