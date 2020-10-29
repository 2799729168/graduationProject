package com.myclient.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pb
 */
@RestController
public class TestController {
    @GetMapping("/r/test")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String Test(){
        System.out.println("成功");
        return "ssss";
    }
}
