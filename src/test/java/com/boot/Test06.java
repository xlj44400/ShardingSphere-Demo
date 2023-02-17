package com.boot;

import com.boot.dao.OrderMapper;
import com.boot.entity.Order;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Test06 {

    @Autowired
    private OrderMapper orderMapper;

    //根据hint策略进行强制分库分表插入数据到ds1的order_2表
    @Test
    void addOrderByHintShardingTableAndDataBase(){

        HintManager hintManager = HintManager.getInstance();

        //实现指定操作ds1的order_2表
        hintManager.addDatabaseShardingValue("order",1);//添加分库的值为1
        hintManager.addTableShardingValue("order",2); //添加分表的值为2

        for (int i = 0; i < 10; i++) {

            Order order = new Order();

            //orderid不需要自己手动插入了！
            order.setOrderInfo("订单信息"+(i+1))
                    .setUserId(Long.parseLong(""+(1001+i)));

            orderMapper.insert(order);
        }

        hintManager.close(); //用完之后要调用close方法。
    }

    //利用Hint策略查询ds1的order_2表
    @Test
    void selectAllByHintShardingTableAndDataBase(){


        HintManager hintManager = HintManager.getInstance();

        //实现指定操作ds1的order_2表
        hintManager.addDatabaseShardingValue("order",1);//添加分库的值为1
        hintManager.addTableShardingValue("order",2); //添加分表的值为2


        List<Order> orders = orderMapper.selectList(null);

        orders.forEach(System.out::println);

        hintManager.close(); //用完之后要调用close方法。

    }


}
