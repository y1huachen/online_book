/**
 * FileName:IndexController
 * Author:  y1huachen
 * Date:    2020-04-17 22:17
 * Description:主页
 */
package com.y1huachen.ajax_view.controller;

import com.y1huachen.ajax_view.bean.Environment;
import com.y1huachen.ajax_view.bean.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * (一句话功能简述) <br>
 * (主页)
 *
 * @author y1huachen
 * @create 2020-04-17
 * @since 1.0.0
 */

@Controller
public class IndexController {

  int count = 20;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

  @RequestMapping("/")
  public String getIndex() {
    return "index";
  }

  @RequestMapping("/data")
  @ResponseBody
  public Map<String, Object> getData() {
    int response_text = count + 1;
    count += (int) (Math.random() * 10) - 5;
    Map<String, Object> data = new HashMap<>();
    data.put("count", response_text);
    return data;
  }

  @RequestMapping("/m-view")
  public String getMainView() {
    return "main_view";
  }

  @RequestMapping("/get_environment")
  @ResponseBody
  public List<Environment> queryEnvironment() {
    List<Environment> data = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      Environment e = new Environment();
      e.setTime(String.valueOf(i + 1));
      int temp = (int) (Math.random() * 20 + 20);
      e.setTemp(String.valueOf(temp));
      int hum = (int) (Math.random() * 100);
      e.setHum(String.valueOf(hum));
      int co2 = (int) (Math.random() * 5000);
      e.setCo2(String.valueOf(co2));
      int light = (int) (Math.random() * 1000);
      e.setLight(String.valueOf(light));
      data.add(e);
    }
    return data;
  }

  @RequestMapping("/search-page")
  public String getHistoryPage() {
    return "history";
  }

  @PostMapping("/search")
  @ResponseBody
  public List<Environment> search(@ModelAttribute Search search) {
    if ("".equals(search.getTime())) {
      System.out.println("设置默认参数");
    } else {
      System.out.println("search=" + search.getTime());
    }
    List<Environment> data = new ArrayList<>(100);
    for (int i = 0; i < 100; i++) {
      Environment e = new Environment();
      e.setTime(String.valueOf(i + 1));
      int temp = (int) (Math.random() * 40);
      e.setTemp(String.valueOf(temp));
      int hum = (int) (Math.random() * 100);
      e.setHum(String.valueOf(hum));
      int co2 = (int) (Math.random() * 5000);
      e.setCo2(String.valueOf(co2));
      int light = (int) (Math.random() * 1000);
      e.setLight(String.valueOf(light));
      data.add(e);
    }
    return data;
  }

  @RequestMapping(value = "/weblogin.json")
  public ModelAndView webloginURL(HttpServletRequest request){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("msg","hello");
    return modelAndView;
  }

  @RequestMapping("/login/test")
  public String getLoginPage() {
    return "logintest";
  }

  @PostMapping("/download/test")
  @ResponseBody
  public Integer download(HttpServletResponse response) {
    // 配置文件下载
    response.setHeader("content-type", "application/octet-stream");
    response.setContentType("application/octet-stream");
    // 下载文件能正常显示中文
    try {
      response.setHeader("Content-Disposition", "attachment;filename=IndexController.java");
      String baseUrl = "src/main/java/com/y1huachen/ajax_view/controller/";
      Path path = Paths.get(baseUrl + "IndexController.java");
      if(downloadNovel(path.toFile(),response.getOutputStream())){
        System.out.println("开始下载");
        Thread.sleep(5000);
        System.out.println("下载成功");
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("下载失败");
    return null;
  }

  public static boolean downloadNovel(File file, OutputStream outputStream){
    FileInputStream fis=null;
    BufferedInputStream bis=null;
    try {
      // 实现文件下载
      byte[] buffer = new byte[1024];
      fis = new FileInputStream(file);
      bis = new BufferedInputStream(fis);
      OutputStream os= outputStream;
      int i = bis.read(buffer);
      while (i != -1) {
        os.write(buffer, 0, i);
        i = bis.read(buffer);
      }
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }finally {
      if (bis != null) {
        try {
          bis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      if (fis != null) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return false;
  }
}
