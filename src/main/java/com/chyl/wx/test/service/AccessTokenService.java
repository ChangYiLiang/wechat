package com.chyl.wx.test.service;

import com.chyl.wx.test.config.WechatException;

/**
 * @Author: chyl
 * @Date: 2019/6/5 14:56
 */
public interface AccessTokenService {
    String getAccessToken() throws WechatException;
}
