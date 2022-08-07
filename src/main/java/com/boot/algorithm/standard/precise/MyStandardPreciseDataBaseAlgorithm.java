package com.boot.algorithm.standard.precise;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.math.BigInteger;
import java.util.Collection;

/**
 * 该类适用于=、in操作
 */


// MyStandardPreciseDataBaseAlgorithm：自定义分数据库的standard策略的精准分片类
public class MyStandardPreciseDataBaseAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     *
     * @param collection 数据源名称集合。ds1和ds2
     * @return
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        //实现ds$->{order_id % 2 + 1}算法

        Long value = preciseShardingValue.getValue();//拿到分片键的值。也就是order_id的值

        BigInteger bigInteger = BigInteger.valueOf(value);

        BigInteger dataSourceNumber = bigInteger.mod(new BigInteger("2")).add(new BigInteger("1"));

        //最终查询的数据源名称
        String dataSourceName="ds"+dataSourceNumber; //ds1或者ds2

        if (collection.contains(dataSourceName)) { //判断dataSourceName是否真实存在
            return dataSourceName;
        }
        throw new UnsupportedOperationException("MyStandardPreciseDataBaseAlgorithm没有找到指定数据源名称");
    }
}
