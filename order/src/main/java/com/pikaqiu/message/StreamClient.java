package com.pikaqiu.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.handler.annotation.SendTo;

/**
 * Created by Administrator on 2018/7/8.
 */
public interface StreamClient {

    String input = "yourMessage";

    String input1 = "myMessage";

    /*@Input(StreamClient.input)
    SubscribableChannel input();*/

    @Output(StreamClient.input)
    MessageChannel output();

    @Input(StreamClient.input1)
    SubscribableChannel input1();

    /*@Output(StreamClient.input)
    MessageChannel input1();*/
}
