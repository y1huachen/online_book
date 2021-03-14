/**
 * FileName:ContentService
 * Author:  y1huachen
 * Date:    2020-06-02 16:02
 * Description:内容业务层
 */
package com.lican.book.service;

import com.lican.book.bean.Chapter;
import com.lican.book.bean.Content;
import com.lican.book.dao.ContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *(一句话功能简述) <br>
 *(内容业务层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@Service
public class ContentService {

  @Autowired
  private ContentDao contentDao;

  public Content findByChapter(Integer chapterId){
    return contentDao.findByChapter(chapterId);
  }

  public void add(Integer chapterId, Content content){
    Chapter chapter = new Chapter();
    chapter.setId(chapterId);
    content.setChapter(chapter);
    contentDao.save(content);;
  }

}
