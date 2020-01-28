package com.pikaqiu.message;

import com.pikaqiu.OrderApplicationTests;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/7/8.
 */
@Component
public class MqReceiverTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now  我的" + new Date());
    }


    @Test
    public void sendGirl() {
        amqpTemplate.convertAndSend("youExchange", "pikaqiu", "now  你的 " + new Date());
    }

    @Test
    public void sendGirl1() {
        amqpTemplate.convertAndSend("myExchange", "xiaozhangqing", "now  我的 " + new Date());
    }
}