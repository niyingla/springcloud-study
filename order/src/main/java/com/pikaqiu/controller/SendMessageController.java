package com.pikaqiu.controller;

import com.pikaqiu.message.StreamClient;
import com.pikaqiu.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Administrator on 2018/7/8.
 */
@RestController
//绑定对应stream 输入输出
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

    @Autowired
    @Qualifier(StreamClient.input)
    private MessageChannel messageChannel;

/*    @GetMapping("/sendStringMessage")
    public void process() {
        String message = "pikaqiu  ------------- hehe" + new Date();
        streamClient.output().send(MessageBuilder.withPayload(message).build());
    }*/

    @GetMapping("/sendBeanMessage")
    public void sendBeanMessage() {
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("哈哈");
        resultVO.setCode(111);
        //streamClient.output().send(MessageBuilder.withPayload(resultVO).build());
        streamClient.output().send(MessageBuilder.withPayload(resultVO).build());
    }
}
