package com.boot;

import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test06 {

    @Autowired
    private OrderMapper orderMapper;

    //根据hint策略进行分库分表
    @Test
    void addOrderByHintShardingTableAndDataBase(){

        for (int i = 0; i < 10; i++) {

            Order order = new Order();

            //orderid不需要自己手动插入了！
            order.setOrderInfo("订单信息"+(i+1))
                    .setUserId(Long.parseLong(""+(1001+i)));

            orderMapper.insert(order);
        }

    }

    //查询全部数据
    @Test
    void selectAllByHintShardingTableAndDataBase(){

        List<Order> orders = orderMapper.selectList(null);

        orders.forEach(System.out::println);

    }


}
