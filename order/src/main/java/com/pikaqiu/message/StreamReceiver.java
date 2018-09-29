package com.pikaqiu.message;

import com.pikaqiu.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/7/8.
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {


    /**接受string 信息  也可以用来接收对象 需要json转换
     * @param message
     */
   /* @StreamListener(StreamClient.input)
    public void process(Message message) {
        log.info("StreamReceiver: {}", new String((byte[]) message.getPayload()));
    }
*/
    /**直接接受对象 它会自动转换
     * @param resultvo
  /*   *//*
    @StreamListener(StreamClient.input)
    public void process(ResultVO resultvo) {
        log.info("resultVo:{}", resultvo);
    }*/


    /**
     * 当接收到消息时  会往sendTo 发送一条新的消息
     * @param resultvo
     * @return
     */
    @StreamListener(StreamClient.input)
    @SendTo(StreamClient.input1)
    public String process1(ResultVO resultvo) {
        log.info("resultVo1:{}", resultvo);
        return "收到啦";
    }

    @StreamListener(StreamClient.input1)
    public void process2(String message) {
        log.info("消息:{}", message);
    }
}
