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

    //查询指定order_id的数据
    @Test
    void selectOrderBystandardShardingTableAndDatabase_orderid(){

        QueryWrapper<Order> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("order_id",763033347873046528L);
        List<Order> orders = orderMapper.selectList(objectQueryWrapper);

        orders.forEach(System.out::println);

    }

    //查询order_id在一个范围的数据(inline策略会报错）
    @Test
    void selectOrderByStandardShardingTableAndDatabase_between(){

        QueryWrapper<Order> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.between("order_id",762681526704930816L,862681526830759936L);

        //lt是小于、gt是大于，这些inline策略都是不支持的
//        objectQueryWrapper.lt("order_id",762681526704930816L);

        List<Order> orders = orderMapper.selectList(objectQueryWrapper);

        orders.forEach(System.out::println);

    }

}
