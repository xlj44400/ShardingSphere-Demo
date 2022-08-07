package com.boot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test05 {

    @Autowired
    private OrderMapper orderMapper;

    //根据complex策略进行分库和分表
    @Test
    void addOrderBycomplexShardingTableAndDatabase(){

        for (int i = 0; i < 10; i++) {

            Order order = new Order();

            order.setOrderInfo("订单信息"+(i+1))
                    .setUserId(Long.parseLong(""+(1001+i)));

            orderMapper.insert(order);
        }

    }

    //查询指定order_id和user_id的数据（并且order_id和user_id同时都是分片键）
    @Test
    void selectOrderBystandardShardingTableAndDatabase_orderidAndUserid(){

        //select * from order where order_id between 663033347873046528L and 763033347873046528L and user_id=1003
        QueryWrapper<Order> objectQueryWrapper = new QueryWrapper<>();

        objectQueryWrapper.between("order_id",663033347873046528L,763033347873046528L);

        objectQueryWrapper.eq("user_id",1003L);

        List<Order> orders = orderMapper.selectList(objectQueryWrapper);

        orders.forEach(System.out::println);

    }


}
