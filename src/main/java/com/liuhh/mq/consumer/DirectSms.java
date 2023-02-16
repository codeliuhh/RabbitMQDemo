package com.liuhh.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: liuhh
 * @Date: 2022/8/21
 */
@RabbitListener(queues = "direct_sms_queue")
@Component
public class DirectSms {

    @RabbitHandler
    public void directSms(String message){
        System.out.println("direct sms, message is " + message);
    }
}
