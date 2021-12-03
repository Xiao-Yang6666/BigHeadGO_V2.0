package com.bigheadgo.aspect;

import com.bigheadgo.annotation.SysLogAnnotation;
import com.bigheadgo.entity.SysLog;
import com.bigheadgo.utils.JedisUtil;
import com.bigheadgo.utils.MyUtils;
import com.bigheadgo.utils.SerializeUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * 用于记录系统日志的切面类
 * 生产者
 * <p>
 * author: xiaoYang
 * time: 2021/12/2 0:42
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {
    // 日志线程池
    @Resource(name = "sysThreadPool")
    private Executor threadPoolTaskExecutor;
    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 切点 这里设置为controller层
     */
    @Pointcut("execution(* com.bigheadgo.controller..*.*(..))")
    private void controllerAspect() {
    }//定义一个切入点

    /**
     * 环绕通知
     *
     * @param joinPoint 代理类
     * @return 执行结果
     */
    @Around(value = "controllerAspect()")
    private Object aroundServiceLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录日志 内部类
        RecordThread recordThread = new RecordThread();

        // 获取方法执行的时间
        long begin = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long end = System.currentTimeMillis();

        // RequestContextHolder是使用spring获取servlet的方法
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 向内部类设置需要的东西
        recordThread.setJoinPoint(joinPoint);
        recordThread.setBegin(begin);
        recordThread.setEnd(end);
        recordThread.setObj(obj);
        recordThread.setRequest(request);

        // 提交到线程池中
        threadPoolTaskExecutor.execute(recordThread::SysLog);

        // 返回结果
        return obj;
    }

    /**
     * 内部类
     * 向数据库中写日志
     */
    @Data
    private class RecordThread {
        // 代理类
        private ProceedingJoinPoint joinPoint;
        // request对象
        private HttpServletRequest request;
        // 代理方法执行前时间
        private long begin;
        // 代理方法执行后时间
        private long end;
        // 方法执行结果
        private Object obj;


        // 具体执行的方法
        private void SysLog() {
            // 拦截的实体类，就是当前正在执行的controller
            Object target = joinPoint.getTarget();
            // 方法名
            String methodName = joinPoint.getSignature().getName();

            // 放行查看日志的接口 放行心跳检测订单日志
            if ("showSysLog".equals(methodName) || "myNewOrders".equals(methodName)) {
                return;
            }

            // 拦截的参数
            Object[] args = joinPoint.getArgs();

            // 拦截的放参数类型
            Signature sig = joinPoint.getSignature();
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            // 转换类型
            MethodSignature msig = (MethodSignature) sig;
            Class[] parameterTypes = msig.getMethod().getParameterTypes();

            // 获得被拦截的方法
            Method method = null;
            try {
                method = target.getClass().getMethod(methodName, parameterTypes);
            } catch (Exception e) {
                log.error("方法不为 public," + e);
            }

            // 系统日志实体类
            SysLog sysLog = new SysLog();

            // 如果获取到了被拦截的方法
            if (null != method) {
                // 判断该方法是否有自定义注解 没有注解直接跳过
                if (method.isAnnotationPresent(SysLogAnnotation.class)) {
                    // 方法上的注解
                    SysLogAnnotation sysLogAnnotation = method.getAnnotation(SysLogAnnotation.class);
                    // 注解的模块名
                    sysLog.setModule(sysLogAnnotation.module());
                    // 注解的方法名
                    sysLog.setMethod(sysLogAnnotation.method());
                } else {
                    return;
                }
            }

            // 获取登录用户信息
            sysLog.setUser(request.getSession().getAttribute("user") + "");
            // 获取用户的ip
            sysLog.setIp(MyUtils.getIpAddr(request));
            // 执行时间
            sysLog.setResponseDate((end - begin) + " ms");
            // 系统当前时间
            sysLog.setDates(MyUtils.getTime());
            // 响应结果
            sysLog.setCommit(obj.toString().startsWith("200", 8) ? "成功" : "失败");
            // 序列化对象 提交进redis队列
            long length = jedisUtil.lpush("sysLog", SerializeUtil.serialize(sysLog));
        }
    }

}
