/**
 * FileName:Utils
 * Author:  y1huachen
 * Date:    2020-06-07 11:57
 * Description:工具类
 */
package com.lican.book.utils;

import com.lican.book.bean.Novel;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *(一句话功能简述) <br>
 *(工具类)
 *
 *@author y1huachen
 *@create 2020-06-07
 *@since 1.0.0
 */

public class Utils {

  private static final String baseUrl = "src/main/resources/static/novels/"; // 小说存储根目录
  private static final String[] UNITS = { "", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千", };
  private static final String[] NUMS = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", };

  /**
   * 阿拉伯数字转中文数字
   * @param value
   * @return
   */
  public static String number2Chinese(int value) {

    String result = ""; //转译结果

    for (int i = String.valueOf(value).length() - 1; i >= 0; i--) {//String.valueOf(value) 转换成String型得到其长度 并排除个位,因为个位不需要单位
      int r = (int) (value / Math.pow(10, i));//value / Math.pow(10, i) 截位匹配单位
      result += NUMS[r % 10] + UNITS[i];
    }

    result = result.replaceAll("零[十, 百, 千]", "零");//匹配字符串中的 "零[十, 百, 千]" 替换为 "零"
    result = result.replaceAll("零+", "零");//匹配字符串中的1或多个 "零" 替换为 "零"
    result = result.replaceAll("零([万, 亿])", "$1");
    result = result.replaceAll("亿万", "亿"); //亿万位拼接时发生的特殊情况

    if (result.startsWith("一十")) { //判断是否以 "一十" 开头 如果是截取第一个字符
      result = result.substring(1);
    }

    if (result.endsWith("零")) { //判断是否以 "零" 结尾 如果是截取除 "零" 外的字符
      result = result.substring(0, result.length() - 1);
    }

    return result;
  }

  public static boolean saveNovel(Novel novel, String[] contents){
    try {
      Integer novelId = novel.getId();
      String novelName = novel.getName();
      String filename = novelId + "/"+novelName+".txt";
      Path dir = Paths.get(baseUrl + novelId);
      Path path = Paths.get(baseUrl + filename);
      if(!Files.exists(dir)){
        Files.createDirectories(dir);
      }
      if(!Files.exists(path)){
        Files.createFile(path);
      }
      BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8,
              StandardOpenOption.APPEND);
      for(String content:contents) {
        writer.write(content);
        writer.newLine();
        writer.newLine();
      }
      writer.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  public static File existsNovel(Novel novel){
    Integer novelId = novel.getId();
    String novelName = novel.getName();
    String filename = novelId + "/"+novelName+".txt";
    Path path = Paths.get(baseUrl + filename);
    if(!Files.exists(path)){
      return null;
    }
    return path.toFile();
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
