/**
 * FileName:ChapterService
 * Author:  y1huachen
 * Date:    2020-06-02 10:13
 * Description:章节业务层
 */
package com.lican.book.service;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Content;
import com.lican.book.bean.Novel;
import com.lican.book.dao.ChapterDao;
import com.lican.book.dao.ContentDao;
import com.lican.book.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 *(一句话功能简述) <br>
 *(章节业务层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@Service
public class ChapterService {

  @Autowired
  private ChapterDao chapterDao;
  @Autowired
  private ContentDao contentDao;

  public Chapter add(Chapter chapter){
    Integer number = chapterDao.findNovelMaxChapter(chapter.getNovel().getId());
    if(number == null){
      number = 0;
    }
    number++;
    chapter.setNumber(number);
    chapter.setChineseNumber(Utils.number2Chinese(number));
    chapter.setCreateTime(new Timestamp(System.currentTimeMillis()));
    chapterDao.save(chapter);
    return chapter;
  }

  public List<Chapter> findAllChapter(Integer novelId){
    return chapterDao.findByNovel(novelId);
  }

  public Chapter findLastChapter(Integer novelId){
    return chapterDao.findNovelLastChapter(novelId);
  }

  public Chapter findByNumber(Integer novelId, Integer number){
    return chapterDao.findByNovelAndNumber(novelId,number);
  }

  public Integer findMaxNumber(Integer novelId){
    return chapterDao.findNovelMaxChapter(novelId);
  }
}
