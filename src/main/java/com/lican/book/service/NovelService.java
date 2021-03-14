/**
 * FileName:NovelService
 * Author:  y1huachen
 * Date:    2020-06-02 9:07
 * Description:小说业务层
 */
package com.lican.book.service;

import com.lican.book.bean.Category;
import com.lican.book.bean.Chapter;
import com.lican.book.bean.Novel;
import com.lican.book.dao.ChapterDao;
import com.lican.book.dao.NovelDao;
import com.lican.book.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 *(一句话功能简述) <br>
 *(小说业务层)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

@Service
public class NovelService {

  @Autowired
  private NovelDao novelDao;
  @Autowired
  private ChapterDao chapterDao;
  @Autowired
  private CategoryService categoryService;

  public List<Novel> findNewAll() {
    return novelDao.findNewAll();
  }

  public List<Novel> findNewByCategory(Integer categoryID){
    return novelDao.findNewByCategory(categoryID);
  }

  public List<Novel> findAll() {
    return novelDao.findAll();
  }

  public List<Novel> findByCategory(Integer categoryID){
    return novelDao.findByCategory(categoryID);
  }

  public List<Chapter> findWithChapterByCategory(Integer categoryId){
    return chapterDao.findAllNovelWithLastChapterByCategoryId(categoryId);
  }

  public List<Chapter> findWithChapter(){
    return chapterDao.findAllNovelWithLastChapter();
  }

  public Novel findById(Integer id) {
    return novelDao.findById(id).get();
  }

  public boolean add(Novel novel) {
    Novel novel1 = findByNameAndAuthor(novel.getName(),novel.getAuthor());
    if(novel1==null){
      if(novel.getCoverUrl()==null||novel.getCoverUrl()=="")
        novel.setCoverUrl("novel/default/cover.png");
      if(novel.getIntroduction()==null||novel.getIntroduction()=="")
        novel.setIntroduction("这本书还没有简介...");
      novel.setTotalWords(0);
      novel.setCountComments(0);
      novel.setViews(0);
      novel.setStatus(1);
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      novel.setCreateTime(timestamp);
      Calendar c = Calendar.getInstance();
      c.setTime(timestamp);
      // 默认三年过期
      c.add(Calendar.YEAR, 3);
      novel.setExpireTime(new Timestamp(c.getTimeInMillis()));
      novelDao.save(novel);
      // 更新分类小说数
      Category category = novel.getCategory();
      category.setCountNovels(category.getCountNovels()+1);
      categoryService.update(category);
      String[] contents = new String[1];
      contents[0] = "《"+novel.getName()+"》\t"+novel.getAuthor()+" 著";
      if(Utils.saveNovel(novel,contents)){
        System.out.println("创建小说“"+novel.getName()+"”成功");
      } else{
        System.out.println("创建小说失败");
      }
      return true;
    }
    return false;
  }

  // 更新书籍简介
  public Novel updateIntroduction(Novel novel) {
    Novel novel1 = novelDao.getOne(novel.getId());
    novel1.setIntroduction(novel.getIntroduction());
    novelDao.save(novel1);
    return novel1;
  }

  // 更新书籍封面地址
  public Novel updateCoverUrl(Novel novel) {
    Novel novel1 = novelDao.getOne(novel.getId());
    novel1.setCoverUrl(novel.getCoverUrl());
    novelDao.save(novel1);
    return novel1;
  }

  // 更新总字数
  public Novel updateTotalWords(Novel novel) {
    Novel novel1 = novelDao.getOne(novel.getId());
    novel1.setTotalWords(novel.getTotalWords());
    novelDao.save(novel1);
    return novel1;
  }

  // 更新评论数
  public Novel updateCountComments(Novel novel) {
    Novel novel1 = novelDao.getOne(novel.getId());
    novel1.setCountComments(novel.getCountComments());
    novelDao.save(novel1);
    return novel1;
  }

  // 更新已看人数
  public Novel updateViews(Novel novel) {
    Novel novel1 = novelDao.getOne(novel.getId());
    novel1.setViews(novel.getViews());
    novelDao.save(novel1);
    return novel1;
  }

  // 更新状态
  public Novel updateStatus(Novel novel) {
    Novel novel1 = novelDao.getOne(novel.getId());
    novel1.setStatus(0);
    novelDao.save(novel1);
    return novel1;
  }

  public Novel findByNameAndAuthor(String name,String author){
    Novel novel = novelDao.findByNameAndAuthor(name,author);
    return novel;
  }
}
