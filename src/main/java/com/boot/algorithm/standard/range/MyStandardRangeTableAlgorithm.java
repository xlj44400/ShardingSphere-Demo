package com.boot.algorithm.standard.range;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;


/**
 * 该类适用于BETWEEN AND、>、 <、 >=、 <= 操作
 */

//1：MyStandardRangeTableAlgorithm：自定义分表的standard策略的范围分片类
//2：RangeShardingAlgorithm接口的泛型是分片键的类型（我们的分片键是order_id它是Long类型的所以就是Long）
public class MyStandardRangeTableAlgorithm implements StandardShardingAlgorithm<Long> {

    /**
     * 由于是范围分片，所以返回的是一个集合（集合里面的值是表名）
     */
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {

        //例如sql：select * from order where order_id between 200 and 300

        String logicTableName = rangeShardingValue.getLogicTableName(); //逻辑表名：也就是order
        String columnName = rangeShardingValue.getColumnName(); //分片键：也就是order_id
        Long lowerEndpoint = rangeShardingValue.getValueRange().lowerEndpoint(); //between的最小值：在上面的sql就是200
        Long upperEndpoint = rangeShardingValue.getValueRange().upperEndpoint(); //between的最大是：在上面的sql就是300


        List<String> tables = new ArrayList<>(); //需要sharding-jdbc查询的表

        //由于这里是range范围查询，所以要查询全部表，所以要把所有表名添加到集合中

        tables.add(logicTableName+"_1"); //表名：order_1
        tables.add(logicTableName+"_2"); //表名：order_2

        return tables;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        return null;
    }

    @Override
    public Properties getProps() {
        return null;
    }

    @Override
    public void init(Properties properties) {

    }
}
