package com.boot.algorithm.hint;

import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.sharding.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

//HintShardingAlgorithm的泛型就是addDatabaseShardingValue方法的value的类型（我们通常使用Integer）
public class MyHintDataBaseAlgorithm implements HintShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {

        List<String> datasources = new ArrayList<>();
        String dataSourceName="ds"+hintShardingValue.getValues().toArray()[0];
        if(collection.contains(dataSourceName)){
            datasources.add(dataSourceName);
            return datasources;
        }
        throw new UnsupportedOperationException("MyHintDataBaseAlgorithm没有找到数据源名");
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
