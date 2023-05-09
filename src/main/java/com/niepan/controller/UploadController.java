package com.niepan.controller;

import com.niepan.pojo.Result;
import com.niepan.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/*
* 本地存储
* */
@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    /*
     * OSS
     * */
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传,{}",image);
        // 调用oss工具上传
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成,文件访问url为: {}",image);
        return Result.success(url);
    }
}











//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传,{},{},{}",username,age,image);
//        // 获取原始文件名
//        final String originalFilename = image.getOriginalFilename();
//        // 构造唯一的文件名 (不能重符) -uuid(通用唯一识别码)
//        final int i = originalFilename.lastIndexOf(".");
//        String extname = originalFilename.substring(i);
//        String newName = UUID.randomUUID().toString()+extname;
//        // 将接收的文件存储在服务器磁盘目录中
//        image.transferTo(new File("C:\\Users\\Ahanzexi\\Desktop\\"+newName));
//        return Result.success();
//    }


