package com.chyl.wx.test.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: chyl
 * @Date: 2019/6/5 15:30
 */
@Getter
@Setter
public class MessageTemplate {
    /***
     * openId
     */
    private String touser;

    /***
     * 模板id
     */
    private String template_id;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 消息数据
     */
    private MessageData data;

    public MessageTemplate(){}

    public MessageTemplate(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }
}
