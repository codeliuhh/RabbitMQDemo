package com.liuhh.mq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuhh.mq.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: liuhh
 * @Date: 2022/8/24
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
