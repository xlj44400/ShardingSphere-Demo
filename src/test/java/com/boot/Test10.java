package com.boot;

import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test10 {

    @Autowired
    private OrderMapper orderMapper;

    //测试主从同步，读写分离（写操作）
    @Test
    void addOrder(){

        Order order = new Order();

        order.setOrderId(Long.valueOf("166666"))
                .setOrderInfo("测试sharding-proxy主从复制-读写分离")
             .setUserId(1002L);


        orderMapper.insert(order);

    }

    // 测试读写分离（读操作）
    @Test
    void selectOrder(){

        List<Order> orders = orderMapper.selectList(null);

        orders.forEach(System.out::println);

    }


}
