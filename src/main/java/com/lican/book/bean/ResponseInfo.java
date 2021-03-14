/**
 * FileName:ResponseInfo
 * Author:  y1huachen
 * Date:    2020-06-04 20:28
 * Description:返回数据格式
 */
package com.lican.book.bean;

/**
 *(一句话功能简述) <br>
 *(返回数据格式)
 *
 *@author y1huachen
 *@create 2020-06-04
 *@since 1.0.0
 */

public class ResponseInfo<T> {
  /**
   * 状态码
   */
  protected int code;
  /**
   * 响应信息
   */
  protected String msg;
  /**
   * 返回数据
   */
  private T data;

  /**
   * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
   */
  public ResponseInfo() {
    this.code = 0;
    this.msg = "操作成功！";
  }

  /**
   * 若没有数据返回，可以人为指定状态码和提示信息
   * @param code
   * @param msg
   */
  public ResponseInfo(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
   * @param data
   */
  public ResponseInfo(T data) {
    this.data = data;
    this.code = 0;
    this.msg = "操作成功！";
  }

  /**
   * 有数据返回，状态码为 0，人为指定提示信息
   * @param data
   * @param msg
   */
  public ResponseInfo(T data, String msg) {
    this.data = data;
    this.code = 0;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}

