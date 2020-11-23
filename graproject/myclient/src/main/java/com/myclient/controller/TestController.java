package com.myclient.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author pb
 */
@RestController
public class TestController {
    @GetMapping("/r/test")
//    @PreAuthorize("hasAnyAuthority('p1')")

    public String Test(){
        System.out.println("成功");
        return "ssss";
    }
    @GetMapping("/test")
    public void test2(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        httpServletResponse.addCookie(new Cookie("username","test"));
    }
}
