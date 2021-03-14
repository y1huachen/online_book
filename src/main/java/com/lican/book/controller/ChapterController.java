/**
 * FileName:ChapterController
 * Author:  y1huachen
 * Date:    2020-06-02 10:29
 * Description:章节控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Content;
import com.lican.book.service.ChapterService;
import com.lican.book.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *(一句话功能简述) <br>
 *(章节控制层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@RestController("ChapterController")
@RequestMapping("chapter")
public class ChapterController {

  @Autowired
  private ChapterService chapterService;
  @Autowired
  private ContentService contentService;

  /**
   * 获取小说所有章节
   * @param novelId
   * @return
   */
  @RequestMapping("find/novel")
  public List<Chapter> findAllChapter(Integer novelId){
    return chapterService.findAllChapter(novelId);
  }

  /**
   * 获取最后更新的章节
   * @param novelId
   * @return
   */
  @RequestMapping("find/last")
  public Chapter findLastChapter(Integer novelId){
    return chapterService.findLastChapter(novelId);
  }

  /**
   * 获取章节内容
   * @param chapterId
   * @return
   */
  @RequestMapping("find/content")
  public Content findChapterContent(Integer chapterId){
    return contentService.findByChapter(chapterId);
  }

  @RequestMapping("find/max")
  public Integer findMaxNumber(Integer novelId){
    return chapterService.findMaxNumber(novelId);
  }

  @RequestMapping("find/number")
  public Map<String,Object> findChapterByNumber(Integer novelId, Integer number){
    Chapter chapter = chapterService.findByNumber(novelId,number);
    Integer maxNumber = chapterService.findMaxNumber(novelId);
    Content content = contentService.findByChapter(chapter.getId());
    Map<String,Object> map = new HashMap<>();
    map.put("content",content);
    map.put("maxNumber",maxNumber);
    return map;
  }

}
