package com.boot.algorithm.hint;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//HintShardingAlgorithm的泛型就是addTableShardingValue方法的value的类型（我们通常使用Integer）
public class MyHintTableAlgorithm implements HintShardingAlgorithm<Integer> {


    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Integer> hintShardingValue) {

        List<String> tables = new ArrayList<>();
        String tableName="order_"+hintShardingValue.getValues().toArray()[0];
        if(collection.contains(tableName)){
            tables.add(tableName);
            return tables;
        }
        throw new UnsupportedOperationException("MyHintTableAlgorithm没有找到表名");
    }
}
