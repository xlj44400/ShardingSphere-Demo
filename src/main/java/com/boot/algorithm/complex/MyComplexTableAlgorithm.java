package com.boot.algorithm.complex;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.*;

public class MyComplexTableAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {

        //select * from order where order_id between 663033347873046528L and 763033347873046528L and user_id=1003

        String logicTableName = complexKeysShardingValue.getLogicTableName(); //逻辑表名

        //getColumnNameAndRangeValuesMap方法：获取范围（between、>、<）的分片键。（相当于standard策略的范围分片类）
        Range<Long> orderIdRange = complexKeysShardingValue.getColumnNameAndRangeValuesMap().get("order_id");

        //如果我们的sql没有写between and或者其他范围相关的操作符，则orderIdRange就会空，调用下面的Endpoint方法就会NullPointerException。
        //所以一定要进行如下判断
        if(orderIdRange!=null&&orderIdRange.isEmpty()){
            Long lowerEndpoint = orderIdRange.lowerEndpoint(); //663033347873046528L

            Long upperEndpoint = orderIdRange.upperEndpoint(); //763033347873046528L
        }

        //getColumnNameAndShardingValuesMap方法：获取精准（=、in）的分片键。（相当于standard策略的精准分片类）
        Collection<Long> userIdCol = complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("user_id");

        //如果我们的sql没有写=或者in，则userIdCol就会空，那么下面遍历这个集合就会NullPointerException
        //所以一定要进行如下判断.
        //如果userIdCol不为null，则遍历这个集合，实现order_$->{order_id % 2 + 1}算法
        if (userIdCol!=null&&!userIdCol.isEmpty()) {

            //最终查询的表名集合，因为=和in可能有多个值。
            List<String> tables = new ArrayList<>();

            //这里虽然是集合，但是我们很多情况下可以把它想成只有一个元素。
            for (Long value : userIdCol) {
                BigInteger bigInteger = BigInteger.valueOf(value);
                BigInteger tableNumber = bigInteger.mod(new BigInteger("2")).add(new BigInteger("1"));
                String tableName=logicTableName+"_"+tableNumber;
                if(collection.contains(tableName)){
                    tables.add(tableName); //添加到集合中
                }else {
                    throw new UnsupportedOperationException("MyComplexTableAlgorithm没有该表名");
                }
            }
            System.out.println(tables);
            return tables;
        }else { //如果userIdCol集合为null，那么就相当于范围查询，则查询全部表

           return collection; //collection里面有全部表名
        }

    }

}
