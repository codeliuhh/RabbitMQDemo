package com.liuhh.mq.ttl;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuhh
 * @Date: 2022/8/21
 */
@Configuration
public class TTLMqConfig {

    @Value("${spring.rabbitmq.exchange.ttl}")
    private String exchangeName;

    //创建交换机
    @Bean
    public TopicExchange ttlExchange(){
        return new TopicExchange(exchangeName);

    }

    //创建死信交换机
    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange("dead_letter_exchange");
    }

    //创建一个带参数的队列，并设置该队列中消息的过期时间，过期数据一般不会直接删除，而是放到死信队列中
    @Bean
    public Queue ttlQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        args.put("x-dead-letter-exchange", "dead_letter_exchange");
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.queue.test", true, false, false, args);
    }
    //创建死信队列，放进死信队列中的数据为：1.过期数据 2.被拒绝的数据 3.超过队列最大长度的数据
    //----------------------------------------------

    @Bean
    public Queue deadQueue(){
        return new Queue("dead_queue", true);

    }

    @Bean
    public Binding deadDind(){
        return BindingBuilder.bind(deadQueue()).to(deadLetterExchange()).with("dead");
    }


    //----------------------------------------------

    //不带参数的队列，可以指定对应消息过期，过期数据一般直接删除
    @Bean
    public Queue normalQueue(){
        return new Queue("ttl.normal.queue", true);
    }

    //绑定消息
    @Bean
    public Binding ttlBind(){
        return  BindingBuilder.bind(ttlQueue()).to(ttlExchange()).with("ttl");
    }

    @Bean
    public Binding ttlNormalBind(){
        return BindingBuilder.bind(normalQueue()).to(ttlExchange()).with("normal");
    }
}
