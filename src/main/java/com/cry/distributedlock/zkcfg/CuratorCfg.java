package com.cry.distributedlock.zkcfg;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorCfg {

    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                // ip地址端口号
                .connectString("192.168.122.147:2181")
                // 客户端与服务器之间的会话超时时间
                .sessionTimeoutMs(1000000)
                // 客户端与服务端之间的会话超时重连间隔
                .retryPolicy(retryPolicy)
                // 父节点命名空间
                .namespace("locks")
                .build();
        return client;
    }

}
