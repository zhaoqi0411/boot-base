package com.papaxiong.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhaoqi
 * @since 2021-04-15
 */
@Slf4j
@RestController
public class SentinelController {

    @SentinelResource(value = "key1", blockHandler = "block")
    @RequestMapping("/sentinel/test")
    public String testSentinel(Long id) {


        System.out.println("123[" + id + "]");
        return "";

    }


    public String block(Long id, BlockException ex) {
        return "limit";
    }


    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/third/block/test2")
    public String testElas() {
        return "";
    }


    @RequestMapping(value = "/third/block/test")
    public String testBlock() throws InterruptedException {
        log.info("@in{}",Thread.currentThread().getId());

        String s = restTemplate.postForObject("http://localhost:8081/test/block", new HashMap<>(), String.class);

        log.info("@out{},{}",Thread.currentThread().getId(),s);
        return "1";
    }


}
