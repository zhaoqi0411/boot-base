package com.papaxiong.config.extra;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaoqi
 * @since 2021-04-26
 */
@Configuration
public class OssConfig {


    private String accessKeyId = "LTAI5tLYA4jUSkbL7uKdW8Q5";

    private String accessKeySecret = "vVCPVbP6QI2j1yOG0wSeIRklRLsry3";


    @Bean
    public OSS ossClient() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5tLYA4jUSkbL7uKdW8Q5";
        String accessKeySecret = "vVCPVbP6QI2j1yOG0wSeIRklRLsry3";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        return ossClient;
    }


}
