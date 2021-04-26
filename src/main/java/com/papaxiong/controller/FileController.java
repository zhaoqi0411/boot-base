package com.papaxiong.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.papaxiong.model.dto.FileLicenseDTO;
import com.papaxiong.support.Wrapper;
import com.papaxiong.support.model.BusinessBaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Objects;

/**
 * @author zhaoqi
 * @since 2021-04-26
 */
@RestController
@Slf4j
public class FileController {

    @Autowired
    private OSS ossClient;


    @RequestMapping("/file/upload")
    public Wrapper<String> upload(@RequestPart("file") MultipartFile file) throws IOException {

        if (Objects.isNull(file) || file.isEmpty()) {
            throw new BusinessBaseException("文件不存在");
        }

        // 填写字符串。
        PutObjectRequest putObjectRequest = new PutObjectRequest("tiaotiao-bucket", file.getOriginalFilename(),
                new ByteArrayInputStream(file.getBytes()));

        ossClient.putObject(putObjectRequest);



        return Wrapper.ok("ok");

    }


    @RequestMapping("/file/license")
    public Wrapper<String> licenseFile(@RequestBody FileLicenseDTO fileLicenseDTO) {
        String fileKey = fileLicenseDTO.getFileKey();
        // 设置URL过期时间为1小时。
        Date expiration = new Date(new Date().getTime() + 3600 * 1000);
// 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl("tiaotiao-bucket", fileKey, expiration);


        return Wrapper.ok(url.toString());
    }
}
