/**
 * FileName:Category
 * Author:  y1huachen
 * Date:    2020-05-31 15:35
 * Description:分类
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 *(一句话功能简述) <br>
 *(分类)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */
@Entity
@Table(name = "category")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 分类ID
  @Column(length = 20)
  private String name; // 分类名
  private Integer countNovels; // 小说数
  private Timestamp createTime; // 创建时间

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

  public Integer getCountNovels() {
    return countNovels;
  }

  public void setCountNovels(Integer countNovels) {
    this.countNovels = countNovels;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }
}
