package com.myclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pb
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String Test(){
        System.out.println("成功");
        return "ssss";
    }
}