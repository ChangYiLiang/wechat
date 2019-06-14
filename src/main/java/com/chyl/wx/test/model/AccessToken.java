package com.chyl.wx.test.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: chyl
 * @Date: 2019/6/5 11:53
 */
@Getter
@Setter
public class AccessToken {
    private String access_token;
    private Long expires_in;
}
