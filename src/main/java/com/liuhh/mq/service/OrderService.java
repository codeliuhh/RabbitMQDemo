package com.liuhh.mq.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author: liuhh
 * @Date: 2022/8/19
 */
@Service
public class OrderService {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.exchange.direct}")
    private String directName;

    @Value("${spring.rabbitmq.exchange.topic}")
    private String topicName;

    @Resource
    RabbitTemplate rabbitTemplate;

    public void makeOrder(String userId, String goodId, int count){
        System.out.println("-----创建订单---->");
        String routingKey = "";
        String message = "orderId:" + UUID.randomUUID().toString().replaceAll("-","");
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    public void directOrder(String userId, String goodsId, int count){
        System.out.println("--------direct方式创建订单-------");
        String smsKey = "sms";
        String emailKey = "email";
        String message = "direct_order, id = " + goodsId;
        rabbitTemplate.convertAndSend(directName, smsKey, message);
        rabbitTemplate.convertAndSend(directName, emailKey, message);
    }

    public void topicOrder(String goodId){
        System.out.println("---------------topic--------------");
        String routingKey = "liuhh.sms.love.you";
        String message = "topic order, id = " + goodId;
        rabbitTemplate.convertAndSend(topicName, routingKey, message);
    }

    public void ttlOrder(String goodId){
        System.out.println("---------------ttl--------------");
        String routingKey = "ttl";
        String message = "topic order, id = " + goodId;
        rabbitTemplate.convertAndSend("ttl_order_exchange", routingKey, message);
    }

    public void normalOrder(String goodId){
        System.out.println("---------------ttl--------------");
        String routingKey = "normal";
        String message = "normal order, id = " + goodId;
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend("ttl_order_exchange", routingKey, message, messagePostProcessor);
    }

}
