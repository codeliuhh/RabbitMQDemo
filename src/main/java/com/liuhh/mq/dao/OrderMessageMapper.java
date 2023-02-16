package com.liuhh.mq.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuhh.mq.pojo.OrderMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: liuhh
 * @Date: 2022/8/24
 */
@Mapper
public interface OrderMessageMapper extends BaseMapper<OrderMessage> {
}
