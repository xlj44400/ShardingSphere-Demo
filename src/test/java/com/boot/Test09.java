package com.boot;

import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test09 {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void addOrder(){

        for (int i = 0; i < 9; i++) {
            Order order = new Order();

            order.setOrderId(Long.valueOf("1000"+i))
                    . setOrderInfo("Sharding-Proxy success")
                    .setUserId(1001L);


            orderMapper.insert(order);
        }


    }


}
