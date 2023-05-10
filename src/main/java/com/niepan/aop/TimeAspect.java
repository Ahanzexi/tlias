package com.niepan.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect //AOP类
public class TimeAspect {

    @Pointcut("execution(* com.niepan.service.*.*(..))")
    private void pt(){}
    /*
    * 统计业务方法的耗时
    * */
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录开始时间
        final long s = System.currentTimeMillis();
        //2.调用原始方法
        final Object result = joinPoint.proceed();
        //3.记录接收时间
        final long e = System.currentTimeMillis();
        log.info(joinPoint.getSignature()+"方法耗时: {}ms",e-s);
        return result;
    }
}
