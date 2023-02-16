package com.liuhh.mq.consumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: liuhh
 * @Date: 2022/8/21
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "topic_sms_queue", durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "*.sms.#"
))
public class TopicSms {
    @RabbitHandler
    public void smsTopic(String message){
        System.out.println("topic sms, message = " + message);
    }
}
