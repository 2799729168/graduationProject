package com.pubutils.services;

import com.pubutils.entity.OauthClientDetails;
import com.pubutils.mapper.OauthClientDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author pb
 */
@Service
public class OauthClientService {
    @Autowired
    private OauthClientDetailsMapper oauthClientDetailsMapper;
    public void insertClientDetail(OauthClientDetails oauthClientDetails){
        this.oauthClientDetailsMapper.insertSelective(oauthClientDetails);
    }
}
