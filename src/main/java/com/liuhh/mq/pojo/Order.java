package com.liuhh.mq.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: liuhh
 * @Date: 2022/8/23
 */
@Data
@TableName("order_info")
public class Order implements Serializable {

    private static final long serialVersionUID = 72376155071513390L;
    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField("content")
    private String content;

    @TableField("user_id")
    private int userId;

    @TableField("create_time")
    private Date createTime;
}
