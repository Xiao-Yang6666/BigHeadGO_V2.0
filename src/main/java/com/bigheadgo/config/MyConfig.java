package com.bigheadgo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的配置类
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 0:15
 */
@Configuration
public class MyConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //此方法返回可用处理器的虚拟机的最大数量; 不小于1
        // int core = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(3);//设置核心线程数
        executor.setMaxPoolSize(5);//设置最大线程数
        executor.setKeepAliveSeconds(30);//除核心线程外的线程存活时间
        executor.setQueueCapacity(10);//如果传入值大于0，底层队列使用的是LinkedBlockingQueue,否则默认使用SynchronousQueue
        executor.setThreadNamePrefix("thread-execute");//线程名称前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//设置拒绝策略
        return executor;
    }

//    @Bean
//    public HttpMessageConverter responseBodyConverter() {
//        //解决返回值中文乱码
//        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
//    }

}
