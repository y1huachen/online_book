/**
 * FileName:LoginInterceptor
 * Author:  y1huachen
 * Date:    2020-06-09 16:28
 * Description:登陆拦截器
 */
package com.lican.book.config.interceptors;

import com.lican.book.bean.Admin;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *(一句话功能简述) <br>
 *(登陆拦截器)
 *
 *@author y1huachen
 *@create 2020-06-09
 *@since 1.0.0
 */

@Component
public class AdminLoginInterceptor implements HandlerInterceptor {

  //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler) throws Exception {
    HttpSession session = request.getSession();
    Admin admin = (Admin) session.getAttribute("admin");
    //如果session中没有user，表示没登陆
    if (admin == null){
      //这个方法返回false表示忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
      //当然你可以利用response给用户返回一些提示信息，告诉他没登陆
      response.sendRedirect("/login/admin?msg=please login first");
      return false;
    }else {
      return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
    }
  }
}
