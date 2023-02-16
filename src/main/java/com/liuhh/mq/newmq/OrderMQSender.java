package com.liuhh.mq.newmq;

import com.alibaba.fastjson.JSON;
import com.liuhh.mq.pojo.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: liuhh
 * @Date: 2022/8/24
 */
@Configuration
public class OrderMQSender {
    @Resource
    RabbitTemplate rabbitTemplate;

    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            if(!ack){
                System.out.println("-----------confirmCallback error-----------");
            }
            try {
                //更新处理
                System.out.println("----------确认中的 s ----------");
                System.out.println(s);
                System.out.println("----------确认中的 s ----------");
                correlationData.getId();
            }catch (Exception e){
            }
        }
    };
    public void sendOrderMessage(Order order){
        String message = JSON.toJSONString(order);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(String.valueOf(order.getId()));
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.convertAndSend("order_new_exchange", "order.new", message, correlationData);
    }
}
