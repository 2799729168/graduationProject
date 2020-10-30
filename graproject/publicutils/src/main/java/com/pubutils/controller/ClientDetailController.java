package com.pubutils.controller;

import com.pubutils.entity.OauthClientDetails;
import com.pubutils.services.OauthClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pb
 */
@RestController
public class ClientDetailController {
    private OauthClientService oauthClientService;
    @PostMapping("client/add")
    public String addClient(@RequestBody OauthClientDetails oauthClientDetails){
        this.oauthClientService.insertClientDetail(oauthClientDetails);
        return "新增客户端信息成功";
    }

}
