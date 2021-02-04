package com.papaxiong.controller;

import com.papaxiong.support.Wrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoqi
 * @since 2021-02-04
 */
@RestController
public class PanHongController {


    @RequestMapping("/test/info")
    public Wrapper<String> testInterface() {

        return Wrapper.ok("ok");
    }
}
