package com.liuhh.mq.newmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuhh
 * @Date: 2022/8/24
 */
@Configuration
public class OrderMQConfig {
    //创建交换机与死信交换机
    @Bean
    public DirectExchange orderNewExchange(){
        return new DirectExchange("order_new_exchange");
    }

    @Bean
    public DirectExchange deadNewExchange(){
        return new DirectExchange("dead_new_exchange");
    }

    //创建队列和死信队列
    @Bean
    public Queue orderNewQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dead_new_exchange");
        args.put("x-dead-letter-routing-key", "dead.new");
        return new Queue("order.new.queue", true);
    }
    @Bean
    public Queue deadNewQueue(){
        return new Queue("dead.new.queue", true);
    }

    //绑定关系
    @Bean
    public Binding orderNewBind(){
        return BindingBuilder.bind(orderNewQueue()).to(orderNewExchange()).with("order.new");
    }

    @Bean
    public Binding deadNewBind(){
        return BindingBuilder.bind(deadNewQueue()).to(deadNewExchange()).with("dead.new");
    }
}
