package com.rcpawn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.io.File;

/*@Slf4j
@RestController
public class UploadTest {

    @RequestMapping("/uploadTest")
    public String uploadTest(String username, Integer age, MultipartFile image) throws Exception {
        log.info("文件上传：{},{},{}", username, age, image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();
        //构造唯一文件名
        int index = originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFileName = UUID.randomUUID() + extname;
        log.info("获取到的新文件名：{}", newFileName);

        //将文件存储在服务器的磁盘目录中
        image.transferTo(new File("E:\\upload\\image\\" + newFileName));
        log.info("文件上传完成");
        return "uploadSuccess";
    }


}*/

@RestController
@Slf4j
public class UploadTest {

    @Value("${upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 获取上传的文件名
            String fileName = file.getOriginalFilename();
            // 保存文件到指定目录
            Path filePath = Paths.get(uploadDir, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("上传成功，{},{}", fileName, filePath);
            return "上传成功";
        } catch (IOException e) {
            return "上传失败: " + e.getMessage();
        }
    }
}