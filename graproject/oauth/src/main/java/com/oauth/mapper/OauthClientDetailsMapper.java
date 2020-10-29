package com.oauth.mapper;


import com.oauth.entity.OauthClientDetails;

/**
 * @author pb
 */
public interface OauthClientDetailsMapper {

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(OauthClientDetails record);


    /**
     * 条件插入
     * @param record
     * @return
     */
    int insertSelective(OauthClientDetails record);
}