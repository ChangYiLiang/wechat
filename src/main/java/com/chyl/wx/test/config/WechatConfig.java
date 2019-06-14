package com.hikvision.config;

/**
 * @Author: chyl
 * @Date: 2019/6/5 19:51
 */
public interface WechatConfig {
    /**
     * 获取accessToken地址
     */
    String ACCESS_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}";

    /**
     * 获取授权信息地址
     */
    String OAUTH_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={APPID}&secret={SECRET}&code={CODE}&grant_type=authorization_code";

    /**
     * 发送微信消息地址
     */
    String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={ACCESS_TOKEN}";

}
