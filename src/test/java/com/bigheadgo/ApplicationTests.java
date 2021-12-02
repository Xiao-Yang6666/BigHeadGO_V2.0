package com.bigheadgo;

import com.bigheadgo.utils.JedisUtil;
import com.bigheadgo.utils.MessagesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootTest
class ApplicationTests {

    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    MessagesUtil mes;

    // 线程池
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    void threadPo() {
        threadPoolTaskExecutor.execute(() -> {
            System.out.println("你啊");
        });
        threadPoolTaskExecutor.execute(() -> {
            System.out.println("你啊");
        });
        threadPoolTaskExecutor.execute(() -> {
            System.out.println("你啊");
        });
        int poolSize = threadPoolTaskExecutor.getPoolSize();
        System.out.println(poolSize);
    }

    @Test
    void contextLoads() {
        boolean ss = jedisUtil.putKey("订单", "222");
        System.out.println(ss);
    }

    @Test
    void messagesUtil() {
        mes.sendMess(3, "1212", "1212", "1212");
    }

}
