/**
 * FileName:User
 * Author:  y1huachen
 * Date:    2020-05-31 22:50
 * Description:用户
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *(一句话功能简述) <br>
 *(用户)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 用户ID
  @Column(length = 40)
  private String username; // 用户名
  @Column(length = 20)
  private String password; // 用户密码
  @Column(length = 100)
  private String introduction; // 用户简介
  @Column(length = 200)
  private String avatarUrl; // 头像地址

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}
