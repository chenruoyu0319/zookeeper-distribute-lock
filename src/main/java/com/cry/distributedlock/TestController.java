package com.cry.distributedlock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class TestController {

    @Autowired
    private OrderService orderService;

    @Value("${server.port}")
    private String port;

    private static final long MAX_WAIT = 3L;

    @Autowired
    CuratorFramework curatorFramework;

    @PostMapping("/stock/reduce")
    public Object reduceStock(Integer id) throws Exception {

//        // 可重入读写锁
//        InterProcessReadWriteLock interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework, "/lock0");
//        // 获取写锁对象
//        InterProcessMutex interProcessMutexWrite = interProcessReadWriteLock.writeLock();
//        // 获取锁
//        interProcessMutexWrite.acquire();
        // 可重入互斥锁
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/product_" + id);
        if (interProcessMutex.acquire(MAX_WAIT, TimeUnit.SECONDS)) {
            try {
                // ...
                orderService.reduceStock(id);
                System.out.println("获取到锁了");
            } catch (Exception e) {
                if (e instanceof RuntimeException) {
                    throw e;
                }
            } finally {
                interProcessMutex.release();
            }
        }
        return "ok:" + port;
    }

}
