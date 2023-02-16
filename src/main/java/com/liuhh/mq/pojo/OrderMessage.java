package com.liuhh.mq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liuhh
 * @Date: 2022/8/23
 */
@Data
@TableName("order_message")
public class OrderMessage {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("order_message")
    private String orderMessage;

    @TableField("create_time")
    private Date createTime;

    @TableField("retry_count")
    private int retryCount;

    @TableField("next_time")
    private Date nextTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("message_id")
    private int messageId;
}
