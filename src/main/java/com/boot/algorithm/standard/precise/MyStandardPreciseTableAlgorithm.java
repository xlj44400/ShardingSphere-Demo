package com.boot.algorithm.standard.precise;

import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Properties;

/**
 * 该类适用于=、in操作
 */

//1：MyStandardPreciseTableAlgorithm：自定义分表的standard策略的精准分片类
//2：PreciseShardingAlgorithm接口的泛型是分片键的类型（我们的分片键是order_id它是Long类型的所以就是Long）
public class MyStandardPreciseTableAlgorithm implements StandardShardingAlgorithm<Long> {

    /**
     * 由于是精准分片，所以返回的是一个字符串（字符串就是指定的表名）
     * @param collection 真实表名集合。order_1和order_2
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        //例如sql：select * from order where order_id=201

        String logicTableName = preciseShardingValue.getLogicTableName(); //逻辑表名。也就是order
        String columnName = preciseShardingValue.getColumnName(); //分片键名。也就是order_id
        Long columnvalue = preciseShardingValue.getValue(); //分片键的值。比如上面的sql，那么这个value就是201


        //实现order_$->{order_id % 2 + 1} 分片算法


        BigInteger bigInteger = BigInteger.valueOf(columnvalue);
        BigInteger tableNumber = bigInteger.mod(new BigInteger("2")).add(new BigInteger("1"));

        //最终需要查询的表名
        String tableName=logicTableName+"_"+tableNumber; //如果tableNumber是1，那么tableName就是order_1
        //判断我们计算出来的表名是否存在
        if(collection.contains(tableName)){
            return tableName; //返回指定的表名
        }
        throw new UnsupportedOperationException("MyStandardPreciseTableAlgorithm没有找到指定的表名");
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
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
