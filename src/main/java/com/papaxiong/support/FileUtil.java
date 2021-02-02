package com.papaxiong.support;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : chenjx
 * create at:  2020/11/15  1:29 下午
 */
public class FileUtil {

    public static String uploadFile(String modelName, MultipartFile file) throws Exception {
        //目标路径
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String filePath = System.getProperty("user.dir")+ File.separator+modelName+ File.separator+sdf.format(new Date())+ File.separator;
        //目标文件名称
        File fileDir = new File(filePath);
        //如果文件目录不存在，就执行创建
        if(!fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        //目标文件全路径
        String finalPath =  filePath +file.getOriginalFilename();
        //创建目标文件
        File targetFile = new File(finalPath);
        //写入本地文件
        FileUtils.copyInputStreamToFile(file.getInputStream(),targetFile);
        //返回文件全路径
        return finalPath;

    }

}
