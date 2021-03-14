/**
 * FileName:ChapterDao
 * Author:  y1huachen
 * Date:    2020-06-02 10:03
 * Description:章节数据接口
 */
package com.lican.book.dao;

import com.lican.book.bean.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *(一句话功能简述) <br>
 *(章节数据接口)
 *
 *@author y1huachen
 *@create 2020-06-02
 *@since 1.0.0
 */

public interface ChapterDao extends JpaRepository<Chapter, Integer> {

  @Query(value =  "SELECT max(number) FROM chapter WHERE novel_id=?",nativeQuery = true)
  Integer findNovelMaxChapter(Integer novelId);

  @Query(value =  "SELECT * FROM chapter WHERE novel_id=?",nativeQuery = true)
  List<Chapter> findByNovel(Integer novelId);

  @Query(value =  "SELECT * FROM chapter WHERE novel_id=? ORDER BY create_time DESC LIMIT 1",nativeQuery = true)
  Chapter findNovelLastChapter(Integer novelId);

  @Query(value =  "SELECT * FROM chapter WHERE novel_id=?1 AND number=?2",nativeQuery = true)
  Chapter findByNovelAndNumber(Integer novelId, Integer number);

  @Query(value =  "SELECT * FROM chapter x WHERE create_time = (SELECT max(create_time) " +
          "FROM chapter y WHERE x.novel_id = y.novel_id AND x.novel_id in" +
          "(SELECT id FROM novel WHERE status=1 AND category_id=?))",nativeQuery = true)
  List<Chapter> findAllNovelWithLastChapterByCategoryId(Integer categoryId);

  @Query(value =  "SELECT * FROM chapter x WHERE create_time = (SELECT max(create_time) " +
          "FROM chapter y WHERE x.novel_id = y.novel_id AND x.novel_id in" +
          "(SELECT id FROM novel WHERE status=1))",nativeQuery = true)
  List<Chapter> findAllNovelWithLastChapter();

}
