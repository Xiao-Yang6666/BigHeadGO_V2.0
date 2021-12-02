package com.bigheadgo.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 切面持久层日志
 * author: xiaoYang
 * time: 2021/11/6 1:23
 */
public class MyAspect {
    // 日志线程池
    @Autowired
    @Qualifier("threadPoolTaskExecutor")
    private ThreadPoolTaskExecutor threadPool;

    /**
     * 环绕通知用于做持久层日志
     */
    public Object aroundServiceLog(ProceedingJoinPoint joinPoint) throws Throwable {
        System.err.println("=========== 环绕通知用于做持久层日志 ============");
        // 获取方法执行的时间
        long begin = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // 创建新子线程用于记录日志
        RecordThread recordThread = new RecordThread(joinPoint, begin, end, obj);
        // 提交到线程池中
        threadPool.execute(recordThread::perfLogs);

        return obj;
    }

    /**
     * 内部类
     * 向数据库中写日志
     */
    class RecordThread {
        private final ProceedingJoinPoint joinPoint;
        private final long begin;
        private final long end;
        private final Object obj;

        public RecordThread(ProceedingJoinPoint joinPoint, long begin, long end, Object obj) {
            this.joinPoint = joinPoint;
            this.begin = begin;
            this.end = end;
            this.obj = obj;
        }

        public void perfLogs() {
            // 类名
            String className = joinPoint.getTarget().getClass().getName();
            // 方法名
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            // 参数
            String paras = "";
            for (int i = 0; i < args.length; i++) {
                paras += "arg" + (i + 1) + ": " + args[i] + ",";
            }

            // 存入数据库
            // logDao.perfLogs(new LogPerf(className, methodName, begin, end, paras, obj));
        }
    }

}
