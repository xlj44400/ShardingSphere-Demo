package com.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TODO: 2022/8/8
 * @author youzhengjie
 */

//lombok注解简化开发
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) //开启链式编程
@TableName("t_config")
public class Config implements Serializable {

    @TableField("config_id")
    private Long configId;

    @TableField("config_info")
    private String configInfo;


}
