package com.liuhh.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liuhh
 * @Date: 2022/8/21
 */
@Configuration
public class DirectMqConfig {

    @Value("${spring.rabbitmq.exchange.direct}")
    private String directExchangeName;

    //创建交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(directExchangeName);
    }

    //创建队列，并持久化
    @Bean
    public Queue directSmsQueue(){
        return new Queue("direct_sms_queue", true);
    }

    @Bean
    public Queue directEmail(){
        return new Queue("direct_email_queue", true);
    }

    @Bean
    public Queue directDx(){
        return new Queue("direct_dx_queue", true);
    }
    //绑定关系
    @Bean
    public Binding smsBindDirect(){
        return BindingBuilder.bind(directSmsQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding emailBindDirect(){
        return BindingBuilder.bind(directEmail()).to(directExchange()).with("email");
    }

    @Bean
    public Binding dxBindDirect(){
        return BindingBuilder.bind(directDx()).to(directExchange()).with("dx");
    }
}
