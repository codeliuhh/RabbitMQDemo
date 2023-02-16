package com.liuhh.mq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: liuhh
 * @Date: 2022/8/24
 */
@Data
@TableName("dispatcher")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dispatcher {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("content")
    private String content;

    @TableField("order_id")
    private int orderId;

    @TableField("sender_id")
    private int senderId;

    @TableField("create_time")
    private Date createTime;
}
