package com.oauth;

import com.oauth.entity.OauthClientDetails;
import com.oauth.mapper.OauthClientDetailsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class OauthApplicationTests {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;

    @Test
    void contextLoads() {
        OauthClientDetails record = new OauthClientDetails();
        record.setClientId("c2");
        record.setClientSecret(new BCryptPasswordEncoder().encode("test"));
        record.setResourceIds("r3");
        record.setScope("ADMIN");
        record.setAuthorizedGrantTypes("authorization_code,password,implicit,client_credentials");
        record.setWebServerRedirectUri("https://www.baidu.com/");
        record.setAuthorities("p1,p2,p3");
        record.setAutoapprove("true");
        oauthClientDetailsMapper.insertSelective(record);
    }

}
