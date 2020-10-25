package com.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pb
 */
@RestController
public class SuccessController {
//    @GetMapping(value = "/success")
//    public String success(){
//        return "登录成功!";
//    }

    @GetMapping(value = "/r/r1")
    public String r1(){
        return "登录成功!";
    }
    @GetMapping(value = "/r/r2")
    public String r2(){
        return "登录成功!";
    }
    @PostMapping(value = "/success")
    public String success(){
        return "登录成功!";
    }

}
