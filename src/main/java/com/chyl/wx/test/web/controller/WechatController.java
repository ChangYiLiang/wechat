package com.chyl.wx.test.web.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.hikvision.config.WechatConfig.OAUTH_URL;

/**
 * @Author: chyl
 * @Date: 2019/6/5 19:34
 */
@Slf4j
@RestController
@RequestMapping("/wechat")
public class WechatController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;

    @GetMapping("/openId/{code}")
    public String wechatOAuth(@PathVariable("code") String code) {
        //获取openId
        String response = restTemplate.getForObject(OAUTH_URL, String.class, appId, appSecret, code);
        JSONObject jsonObject = JSONObject.parseObject(response);
        String openid = jsonObject.getString("openid");
        return openid;
    }

    @GetMapping("/test")
    public String wxTest(@RequestParam Map<String, String> map) {
        log.info(map.get("echostr"));
        return map.get("echostr");
    }
}
