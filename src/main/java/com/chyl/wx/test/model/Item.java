package com.chyl.wx.test.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: chyl
 * @Date: 2019/6/5 15:40
 */
@Setter
@Getter
public class Item {
    /***
     * value
     */
    private String value;

    /**
     * 颜色
     */
    private String color = "#173177";

    public Item() {}

    public Item(String value) {
        this.value = value;
    }

}
