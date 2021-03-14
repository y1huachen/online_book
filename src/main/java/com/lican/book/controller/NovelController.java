/**
 * FileName:NovelController
 * Author:  y1huachen
 * Date:    2020-06-02 9:35
 * Description:小说控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Comment;
import com.lican.book.bean.Novel;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.ChapterService;
import com.lican.book.service.CommentService;
import com.lican.book.service.NovelService;
import com.lican.book.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 *(一句话功能简述) <br>
 *(小说控制层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@Controller("NovelController")
@RequestMapping("novel")
public class NovelController {

  public static boolean isDownloadComplete = false; // 下载是否完成

  @Autowired
  private NovelService novelService;
  @Autowired
  private ChapterService chapterService;
  @Autowired
  private CommentService commentService;

  @RequestMapping("find/category")
  @ResponseBody
  public List<Novel> findAllNovelByCategory(Integer categoryId){
    if(categoryId==null||categoryId == 0){
      return novelService.findAll();
    }
    return novelService.findByCategory(categoryId);
  }

  @RequestMapping("find/new/category")
  @ResponseBody
  public List<Novel> findAllNewNovelByCategory(Integer categoryId){
    if(categoryId==null||categoryId == 0){
      return novelService.findNewAll();
    }
    return novelService.findNewByCategory(categoryId);
  }

  @RequestMapping("find/last/category")
  @ResponseBody
  public List<Chapter> findWithChapterByCategory(Integer categoryId){
    if(categoryId==null||categoryId == 0){
      return novelService.findWithChapter();
    }
    return novelService.findWithChapterByCategory(categoryId);
  }

  @RequestMapping("download/id")
  @ResponseBody
  public ResponseInfo downloadNovel(Integer novelId, HttpServletResponse response){
    isDownloadComplete = false; // 下载开始前恢复未完成状态
    Novel novel = novelService.findById(novelId);
    File file = Utils.existsNovel(novel);
    if(file==null)
      return new ResponseInfo(1,"file not exists!");
    // 配置文件下载
    response.setHeader("content-type", "application/octet-stream");
    response.setContentType("application/octet-stream");
    // 下载文件能正常显示中文
    try {
      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(novel.getName()+".txt", "UTF-8"));
      if(Utils.downloadNovel(file,response.getOutputStream())){
        System.out.println("Download novel《"+novel.getName()+"》successfully!");
        isDownloadComplete = true; // 下载完成
        return null;
      }
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
    return new ResponseInfo(1,"io error");
  }

  /**
   * 根据id查询书籍
   * @param id
   * @return
   */
  @RequestMapping("find/id")
  public String findNovelById(int id, Model model) {
    Novel novel = novelService.findById(id);
    Chapter chapter = chapterService.findLastChapter(id);
    List<Comment> comments = commentService.findByNovel(id);
    List<Chapter> chapters = chapterService.findAllChapter(id);
    model.addAttribute("novel",novel);
    model.addAttribute("chapter",chapter);
    model.addAttribute("comments",comments);
    model.addAttribute("chapters",chapters);

    return "novelInfo";
  }


  @RequestMapping("download/status")
  @ResponseBody
  public ResponseInfo getDownloadStatus() {
    return new ResponseInfo(isDownloadComplete);
  }

  @RequestMapping("download/reset")
  @ResponseBody
  public void resetDownloadStatus() {
    isDownloadComplete = false;
  }
}
