package com.chyl.wx.test;

import com.chyl.wx.test.model.Item;
import com.chyl.wx.test.model.MessageData;
import com.chyl.wx.test.model.MessageTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    private String touser = "obvIV1pvP0IzPxWPWGn4kxkpYDcI";
    private String template_id = "wnuSVrPLDGYsgH8zr_ffuC0uoMl3L6eZDF2jrhIaRfo";
    private String url = "http://118.126.92.168/sendMessage";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void sendMessage() {
        MessageTemplate messageTemplate = new MessageTemplate(touser, template_id);
        MessageData messageDataBean = new MessageData();
        messageDataBean.setFirst(new Item("预约成功！"));
        messageDataBean.setKeyword1(new Item("123栋"));
        messageDataBean.setKeyword2(new Item("1002号"));
        messageDataBean.setKeyword3(new Item("深圳市福田区梅林街道"));
        messageDataBean.setKeyword4(new Item("2019-06-06 18:30:00"));
        messageDataBean.setRemark(new Item("欢迎准时到达"));
        messageTemplate.setData(messageDataBean);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, messageTemplate, String.class);
        System.out.printf(entity.getBody());
    }

}
