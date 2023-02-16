package com.liuhh.mq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: liuhh
 * @Date: 2022/8/19
 */
@RabbitListener(queues = "sms_queue")
@Component
public class SmsConsumer {

    @RabbitHandler
    public void smsCon(String message){
        System.out.println("sms receive is : " + message);
    }
}
