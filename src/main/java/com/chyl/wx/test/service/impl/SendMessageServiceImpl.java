package com.chyl.wx.test.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.chyl.wx.test.config.WechatException;
import com.chyl.wx.test.model.Item;
import com.chyl.wx.test.model.MessageData;
import com.chyl.wx.test.model.MessageTemplate;
import com.chyl.wx.test.service.AccessTokenService;
import com.chyl.wx.test.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.hikvision.config.WechatConfig.SEND_MESSAGE_URL;

/**
 * @Author: chyl
 * @Date: 2019/6/5 14:47
 */
@Slf4j
@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AccessTokenService accessTokenService;
    @Value("${wechat.templateId}")
    private String templateId;


    @Async
    @Retryable(backoff = @Backoff(value = 60000L))
    @Override
    public void sendMessage() throws Exception {
        //获取accessToken
        String accessToken = accessTokenService.getAccessToken();
        //组装微信消息
        MessageTemplate messageTemplate = handleMessageTemplate();
        //发送微信消息
        String response = restTemplate.postForObject(SEND_MESSAGE_URL, messageTemplate, String.class, accessToken);
        JSONObject jsonObject = JSONObject.parseObject(response);
        if(jsonObject != null && jsonObject.getString("errcode").equals("0")){
            log.info("{}:微信消息发送成功");
        }else{
            throw new WechatException(response);
        }
    }

    private MessageTemplate handleMessageTemplate() {
        MessageTemplate messageTemplateBO = new MessageTemplate("", templateId);
        MessageData messageData = new MessageData();
        messageData.setFirst(new Item("预约成功！"));
        messageData.setKeyword1(new Item(""));
        messageData.setKeyword2(new Item(""));
        messageData.setKeyword3(new Item(""));
        messageData.setRemark(new Item("请您按时赴约"));
        messageTemplateBO.setData(messageData);
        return messageTemplateBO;
    }
}
