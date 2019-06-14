package com.chyl.wx.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.chyl.wx.test.config.WechatException;
import com.chyl.wx.test.model.AccessToken;
import com.chyl.wx.test.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.hikvision.config.WechatConfig.ACCESS_URL;

/**
 * @Author: chyl
 * @Date: 2019/6/5 14:56
 */
@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;

    @Override
    @Cacheable(value = "wx_access_token", key = "#root.methodName")
    public String getAccessToken() throws WechatException {
        String response = restTemplate.getForObject(ACCESS_URL, String.class, appId, appSecret);
        AccessToken accessToken = JSON.parseObject(response, AccessToken.class);
        if (accessToken != null) {
            return accessToken.getAccess_token();
        } else {
            //获取失败抛出异常
            throw new WechatException(response);
        }
    }
}
