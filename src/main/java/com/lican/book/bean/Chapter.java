/**
 * FileName:Chapter
 * Author:  y1huachen
 * Date:    2020-05-31 14:49
 * Description:章节
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *(一句话功能简述) <br>
 *(章节)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */

@Entity
@Table(name = "chapter")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Chapter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 章节ID
  private Integer number; // 章节序号
  @Column(length = 100)
  private String chineseNumber; // 章节序号中文名
  @Column(length = 100)
  private String name; // 章节名
  private Timestamp createTime; // 章节更新时间

  @ManyToOne(cascade ={CascadeType.MERGE,CascadeType.REFRESH},optional = false)
  @JoinColumn(name = "novel_id")
  private Novel novel; // 所属小说

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }

  public Novel getNovel() {
    return novel;
  }

  public void setNovel(Novel novel) {
    this.novel = novel;
  }

  public String getChineseNumber() {
    return chineseNumber;
  }

  public void setChineseNumber(String chineseNumber) {
    this.chineseNumber = chineseNumber;
  }
}
