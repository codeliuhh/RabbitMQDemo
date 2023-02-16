package com.liuhh.mq.newmq;

import com.alibaba.fastjson.JSONArray;
import com.liuhh.mq.pojo.Dispatcher;
import com.liuhh.mq.pojo.Order;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.Header;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: liuhh
 * @Date: 2022/8/23
 */
@Configuration
public class OrderMQConsumer {


    @Resource
    JdbcTemplate jdbcTemplate;

    @RabbitListener(queues = "order.new.queue")
    public void messageConsumer(String orderMsg, Channel channel,
                                CorrelationData correlationData,
                                @Header(AmqpHeaders.DELIVERY_TAG) long tag){

        try {
            System.out.println("------> receive message is " + orderMsg);
            //保存，订单派发
            if(StringUtils.isNotBlank(orderMsg)){
                Order order = JSONArray.parseObject(orderMsg, Order.class);
                Dispatcher dispatcher = Dispatcher.builder()
                        .content(order.getContent())
                        .orderId(order.getId())
                        .senderId(1)
                        .createTime(new Date())
                .build();
                String sqlStr = "insert into dispatcher(content, order_id, sender_id, create_time) values (?,?,?,?)";
                int count = jdbcTemplate.update(sqlStr, order.getContent(), order.getId(), 1, new Date());
                if(count <=0 ){
                    System.out.println("订单派发异常！！！！！");
                }
            }
            channel.basicAck(tag, false);
        }catch (Exception e){
            try {
                channel.basicNack(tag, false, false);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
