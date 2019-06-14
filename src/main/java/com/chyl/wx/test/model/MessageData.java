package com.chyl.wx.test.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: chyl
 * @Date: 2019/6/5 15:34
 */
@Getter
@Setter
public class MessageData {
    /**
     * 消息头
     */
    private Item first;

    /**
     * 关键字
     */
    private Item keyword1;

    /**
     * 关键字
     */
    private Item keyword2;

    /**
     * 关键字
     */
    private Item keyword3;

    /**
     * 关键字
     */
    private Item keyword4;

    /**
     * 关键字
     */
    private Item keyword5;

    /**
     * 备注
     */
    private Item remark;
}
