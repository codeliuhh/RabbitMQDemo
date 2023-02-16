package com.liuhh.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuhh
 * @Date: 2022/8/19
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    //创建交换机
    @Bean
    public FanoutExchange createExchange(){
        return new FanoutExchange(exchangeName);
    }

    //创建队列
    @Bean
    public Queue smsQueue(){
        return new Queue("sms_queue", true);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue("email_queue", true);
    }

    @Bean
    public Queue dxQueue(){
        return new Queue("dx_queue", true);
    }

    //绑定
    @Bean
    public Binding smsBind(){
        return BindingBuilder.bind(smsQueue()).to(createExchange());
    }

    @Bean
    public Binding emailBind(){
        return BindingBuilder.bind(emailQueue()).to(createExchange());
    }

    @Bean
    public Binding dxBind(){
        return BindingBuilder.bind(dxQueue()).to(createExchange());
    }
}
