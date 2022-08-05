package com.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TODO: 2022/8/2
 * @author youzhengjie
 */

//lombok注解简化开发
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //开启链式编程
//由于mybatis-plus是在mybatis的基础上建立的，所以也有下面的通病（就是order本身就是关键字，无法被解析，所以需要加上``符号）。
//如果不加上@TableName指定表名，默认的表名就是该类的名称。
@TableName("`order`")
public class Order implements Serializable {

    @TableField("order_id")
    private Long orderId; //订单id

    @TableField("order_info")
    private String orderInfo; //订单信息

    @TableField("user_id")
    private Long userId; //用户id

}
