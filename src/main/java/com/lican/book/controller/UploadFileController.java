/**
 * FileName:UploadFileController
 * Author:  y1huachen
 * Date:    2020-06-03 9:58
 * Description:文件上传控制层
 */
package com.lican.book.controller;

import com.lican.book.bean.Admin;
import com.lican.book.bean.Novel;
import com.lican.book.bean.ResponseInfo;
import com.lican.book.bean.User;
import com.lican.book.service.AdminService;
import com.lican.book.service.NovelService;
import com.lican.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 *(一句话功能简述) <br>
 *(文件上传控制层)
 *
 *@author y1huachen
 *@create 2020-06-03
 *@since 1.0.0
 */

@RestController
@RequestMapping("file")
public class UploadFileController {

  private String baseUrl = "src/main/resources/static/upload/images/";

  @Autowired
  private UserService userService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private NovelService novelService;

  @PostMapping("user/avatar")
  public ResponseInfo userAvatar(Integer userId, @RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return new ResponseInfo(1,"file is empty, try again");
    }
    User user = new User();
    user.setId(userId);
    String pre = "user/"+user.getId();
    String fName = "avatar";
    String typeName = saveFile(pre,fName,file);
    String avatarUrl = pre+"/"+fName+typeName;
    user.setAvatarUrl(avatarUrl);
    userService.updateAvatarUrl(user);
    return new ResponseInfo(0,"upload user avatar success");
  }

  @PostMapping("admin/avatar")
  public ResponseInfo adminAvatar(Integer adminId, @RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return new ResponseInfo(1,"file is empty, try again");
    }
    Admin admin = new Admin();
    admin.setId(adminId);
    String pre = "admin/"+admin.getId();
    String fName = "avatar";
    String typeName = saveFile(pre,fName,file);
    String avatarUrl = pre+"/"+fName+typeName;
    admin.setAvatarUrl(avatarUrl);
    adminService.updateAvatarUrl(admin);
    return new ResponseInfo(0,"upload admin avatar success");
  }

  @PostMapping("novel/cover")
  public ResponseInfo novelCover(Integer novelId, @RequestParam("file") MultipartFile file) {
    if (file.isEmpty()) {
      return new ResponseInfo(1,"file is empty, try again");
    }
    Novel novel = new Novel();
    novel.setId(novelId);
    String pre = "novel/"+novel.getId();
    String fName = "cover";
    String typeName = saveFile(pre,fName,file);
    String coverUrl = pre+"/"+fName+typeName;
    novel.setCoverUrl(coverUrl);
    novelService.updateCoverUrl(novel);
    return new ResponseInfo(0,"upload novel cover success");
  }

  public String saveFile(String pre,String fName,MultipartFile file){
    try {
      byte[] bytes = file.getBytes();
      String fileName = file.getOriginalFilename();
      String typeName = fileName.substring(fileName.lastIndexOf("."));
      Path path = Paths.get(baseUrl + pre);
      if(!Files.exists(path)){
        Files.createDirectories(path);
      }
      Path fPath = Paths.get(path+"/"+fName+typeName);
      Files.write(fPath, bytes);
      return typeName;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
