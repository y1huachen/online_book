/**
 * FileName:NovelController
 * Author:  y1huachen
 * Date:    2020-06-02 10:32
 * Description:小说控制层
 */
package com.lican.book.controller.admin;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Novel;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.service.ChapterService;
import com.lican.book.service.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *(一句话功能简述) <br>
 *(小说控制层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@Controller("AdminNovelController")
@RequestMapping("admin/novel")
public class NovelController {

  @Autowired
  private NovelService novelService;
  @Autowired
  private ChapterService chapterService;

  /**
   * 添加书籍
   * @param novel
   * @return
   */
  @PostMapping("add")
  @ResponseBody
  public ResponseInfo add(Novel novel) {
    if(novelService.add(novel))
      return new ResponseInfo(0,"add novel success");
    return new ResponseInfo(1,"novel already exists");
  }


  @PostMapping("update/introduction")
  @ResponseBody
  public ResponseInfo updateIntroduction(Novel novel) {
    novelService.updateIntroduction(novel);
    return new ResponseInfo(0,"update success");
  }

  @PostMapping("update/cover_url")
  @ResponseBody
  public Novel updateCoverUrl(Novel novel) {
    return novelService.updateCoverUrl(novel);
  }

  @PostMapping("update/total_words")
  @ResponseBody
  public Novel updateTotalWords(Novel novel) {
    return novelService.updateTotalWords(novel);
  }

  @PostMapping("update/count_comments")
  @ResponseBody
  public Novel updateCountComments(Novel novel) {
    return novelService.updateCountComments(novel);
  }

  @PostMapping("update/views")
  @ResponseBody
  public Novel updateViews(Novel novel) {
    return novelService.updateViews(novel);
  }

  @PostMapping("update/status")
  @ResponseBody
  public Novel updateStatus(Novel novel) {
    return novelService.updateStatus(novel);
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
    model.addAttribute("novel",novel);
    model.addAttribute("chapter",chapter);
    return "admin/novelInfo";
  }

}
