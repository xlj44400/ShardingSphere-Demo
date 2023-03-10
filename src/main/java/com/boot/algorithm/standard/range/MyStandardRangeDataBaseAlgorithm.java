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

// MyStandardRangeDataBaseAlgorithm：自定义分数据库的standard策略的范围分片类
public class MyStandardRangeDataBaseAlgorithm implements StandardShardingAlgorithm<Long> {

    /**
     * 由于是范围分片，所以返回的是一个集合（集合里面的值是数据源名）
     */

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {

        List<String> dataSources = new ArrayList<>(); //数据源名称集合

        //由于这里是范围查询，所以全部数据源都要查询，故把所有数据源名称添加进去
        dataSources.add("ds1");
        dataSources.add("ds2");

        return dataSources;
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        return null;
    }

    @Override
    public void init(Properties properties) {
        // 空实现
    }

    @Override
    public Properties getProps() {
        return new Properties();
    }
}
