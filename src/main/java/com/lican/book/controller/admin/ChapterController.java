/**
 * FileName:ChapterController
 * Author:  y1huachen
 * Date:    2020-06-02 10:33
 * Description:章节控制层
 */
package com.lican.book.controller.admin;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Content;
import com.lican.book.bean.Novel;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.ChapterService;
import com.lican.book.service.ContentService;
import com.lican.book.service.NovelService;
import com.lican.book.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *(一句话功能简述) <br>
 *(章节控制层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@RestController("AdminChapterController")
@RequestMapping("admin/chapter")
public class ChapterController {

  @Autowired
  private ChapterService chapterService;
  @Autowired
  private NovelService novelService;
  @Autowired
  private ContentService contentService;

  /**
   * 添加章节
   * @param chapter
   * @return
   */
  @PostMapping("add")
  public ResponseInfo add(Chapter chapter, String content, Integer words){
    // 添加章节，返回章节序号
    Chapter c = chapterService.add(chapter);
    Integer number = c.getNumber();
    String chineseNumber = c.getChineseNumber();
    System.out.println("第"+chineseNumber+"章");
    // 根据小说id与章节序号获取新添加包含id的章节对象
    Chapter chapter1 = chapterService.findByNumber(chapter.getNovel().getId(),number);
    // 添加章节内容，返回章节内容字数
    Content content1 = new Content();
    content1.setContent(content);
    content1.setWords(words);
    contentService.add(chapter1.getId(), content1);
    Novel novel = chapter1.getNovel();
    // 更新总字数
    novel.setTotalWords(novel.getTotalWords() + words);
    novelService.updateTotalWords(novel);
    String chapterContent = "第"+chineseNumber+"章 "+chapter.getName();
    String[] contents = new String[2];
    contents[0] = chapterContent;
    contents[1] = content;
    if(Utils.saveNovel(novel,contents)){
      System.out.println("保存小说“"+novel.getName()+"”的章节["+chapter.getName()+"]成功");
    }else{
      System.out.println("保存章节失败");
    }
    return new ResponseInfo(0,"add novel success");
  }
}
