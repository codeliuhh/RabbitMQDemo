package com.liuhh.mq.service;

import com.liuhh.mq.pojo.Order;
import com.liuhh.mq.pojo.OrderMessage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: liuhh
 * @Date: 2022/8/23
 */
@Service
public class OrderNewService {
    @Resource
    JdbcTemplate jdbcTemplate;


    @Transactional(rollbackFor = Exception.class)
    public void saveOrder(Order order){
        String sqlStr = "insert into order_info(content, user_id, create_time) values (?,?,?)";
        int count = jdbcTemplate.update(sqlStr, order.getContent(), order.getUserId(), order.getCreateTime());
        if(count <= 0){
            System.out.println("订单保存失败！！！！！");
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public void saveOrderMessage(OrderMessage orderMessage){
        String sqlStr = "insert into order_message(order_message, retry_count, next_time, create_time, update_time, message_id) values(?,?,?,?,?,?)";
        int count = jdbcTemplate.update(sqlStr, orderMessage.getOrderMessage(), orderMessage.getRetryCount(), orderMessage.getNextTime(), orderMessage.getCreateTime(),
                                        orderMessage.getUpdateTime(), orderMessage.getMessageId());
        if(count <= 0){
            System.out.println("订单保存失败！！！！！");
        }
    }
}
