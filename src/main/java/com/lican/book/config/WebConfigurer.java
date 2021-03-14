/**
 * FileName:WebConfigurer
 * Author:  y1huachen
 * Date:    2020-06-09 16:25
 * Description:拦截器配置类
 */
package com.lican.book.config;

import com.lican.book.config.interceptors.AdminLoginInterceptor;
import com.lican.book.config.interceptors.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *(一句话功能简述) <br>
 *(拦截器配置类)
 *
 *@author y1huachen
 *@create 2020-06-09
 *@since 1.0.0
 */

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

  @Autowired
  private AdminLoginInterceptor adminLoginInterceptor;
  @Autowired
  private UserLoginInterceptor userLoginInterceptor;

  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // addPathPatterns("/**") 表示拦截所有的请求，
    // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
    // addPathPatterns 用来设置拦截路径，excludePathPatterns 用来设置白名单，也就是不需要触发这个拦截器的路径
    registry.addInterceptor(adminLoginInterceptor).addPathPatterns("/admin/**").excludePathPatterns();
    registry.addInterceptor(userLoginInterceptor).addPathPatterns("/user/**");
  }

  // 这个方法是用来配置静态资源的，比如html，js，css，等等
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {

  }
}
