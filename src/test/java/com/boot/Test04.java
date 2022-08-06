package com.boot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test04 {

    @Autowired
    private OrderMapper orderMapper;

    //根据standard策略进行分库和分表
    @Test
    void addOrderBystandardShardingTableAndDatabase(){

        for (int i = 0; i < 10; i++) {

            Order order = new Order();

            order.setOrderInfo("订单信息"+(i+1))
                    .setUserId(Long.parseLong(""+(1001+i)));

            orderMapper.insert(order);
        }

    }

    //查询全部数据
    @Test
    void selectAllBystandardShardingTableAndDatabase(){

        List<Order> orders = orderMapper.selectList(null);

        orders.forEach(System.out::println);

    }

    //查询指定order_id的数据
    @Test
    void selectOrderBystandardShardingTableAndDatabase_orderid(){

        QueryWrapper<Order> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("order_id",762681526788816896L);
        List<Order> orders = orderMapper.selectList(objectQueryWrapper);

        orders.forEach(System.out::println);

    }


}
