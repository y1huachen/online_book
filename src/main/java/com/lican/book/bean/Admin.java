/**
 * FileName:Admin
 * Author:  y1huachen
 * Date:    2020-05-31 22:58
 * Description:管理员
 */
package com.lican.book.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 *(一句话功能简述) <br>
 *(管理员)
 *
 *@author y1huachen
 *@create 2020-05-31
 *@since 1.0.0
 */

@Entity
@Table(name = "admin")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id; // 管理员ID
  @Column(length = 40)
  private String name; // 管理员名
  @Column(length = 20)
  private String password; // 管理员密码
  @Column(length = 200)
  private String avatarUrl; // 头像地址

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }
}
