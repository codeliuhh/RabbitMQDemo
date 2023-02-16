package com.liuhh.mq.controller;

import com.alibaba.fastjson.JSON;
import com.liuhh.mq.newmq.OrderMQSender;
import com.liuhh.mq.pojo.Order;
import com.liuhh.mq.pojo.OrderMessage;
import com.liuhh.mq.service.OrderNewService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author: liuhh
 * @Date: 2022/8/24
 */
@RestController
@RequestMapping("/order")
public class OrderNewController {

    @Autowired
    OrderMQSender orderMQSender;

    @Autowired
    OrderNewService orderNewService;

    @PostMapping("/send")
    public void sendMessage(@RequestBody Order order){
        order.setCreateTime(new Date());
        orderNewService.saveOrder(order);
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setOrderMessage(JSON.toJSONString(order));
        orderMessage.setRetryCount(0);
        orderMessage.setNextTime(DateUtils.addMinutes(new Date(), 1));
        orderMessage.setMessageId(order.getId());
        orderMessage.setCreateTime(new Date());
        orderMessage.setUpdateTime(new Date());
        orderNewService.saveOrderMessage(orderMessage);
        orderMQSender.sendOrderMessage(order);
    }
}
