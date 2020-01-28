package com.pikaqiu.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/8.
 */
@Component
@Slf4j
public class MqReceiver {

    //1 需要手动创建队列
    // @RabbitListener(queues = "myQueue")

    //2 自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))

    @RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void receive(String message) {
        log.info("myQueue:{}", message);
    }

    //第三种方式 Exchange 和 Queue绑定
    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue"), key = "xiaozhangqing", exchange = @Exchange("myExchange")))
    public void myReceiver(String message) {
        log.info("myExchange:{}", message);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue("myQueue"), key = "pikaqiu", exchange = @Exchange("youExchange")))
    public void youReceiver(String message) {
        log.info("youExchange:{}", message);
    }
}
