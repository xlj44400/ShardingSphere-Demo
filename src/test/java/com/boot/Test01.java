package com.boot;

import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test01 {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void addOrder(){

        for (int i = 0; i < 10; i++) {

            Order order = new Order();

            order.setOrderId(Long.parseLong(""+(i+1)))
                 .setOrderInfo("订单信息"+(i+1))
                 .setUserId(Long.parseLong(""+(1001+i)));

            System.out.println(order);
            orderMapper.insert(order);
        }
    }

    @Test
    void selectOrderList(){

        List<Order> orders = orderMapper.selectList(null);

        orders.forEach(System.out::println);

    }
}
