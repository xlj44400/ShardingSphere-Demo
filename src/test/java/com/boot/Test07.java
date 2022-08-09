package com.boot;

import com.boot.dao.ConfigMapper;
import com.boot.entity.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test07 {

    @Autowired
    private ConfigMapper configMapper;

    @Test
    void addConfigToBroadcastTable(){

        Config config = new Config();

        config.setConfigInfo("配置信息123");

        configMapper.insert(config);

    }



}
