package com.boot.algorithm.complex;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyComplexDataBaseAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, ComplexKeysShardingValue<Long> complexKeysShardingValue) {

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
        //所以一定要进行如下判断。

        //如果userIdCol不为null，则遍历这个集合，实现ds$->{order_id % 2 + 1}算法。
        if (userIdCol!=null&&!userIdCol.isEmpty()) {

            //最终查询的表名集合，因为=和in可能有多个值。
            List<String> datasouces = new ArrayList<>();

            //这里虽然是集合，但是我们很多情况下可以把它想成只有一个元素。
            for (Long value : userIdCol) {
                BigInteger bigInteger = BigInteger.valueOf(value);
                BigInteger dataSourceNumber = bigInteger.mod(new BigInteger("2")).add(new BigInteger("1"));
                String dataSourceName="ds"+dataSourceNumber;
                if(collection.contains(dataSourceName)){
                    datasouces.add(dataSourceName); //添加到集合中
                }else {
                    throw new UnsupportedOperationException("MyComplexDataBaseAlgorithm没有该数据源名");
                }
            }
            return datasouces;
        }else { //如果userIdCol集合为null，那么就相当于范围查询，则查询全部数据源


            return collection; //collection里面有全部数据源名称
        }

    }
}
