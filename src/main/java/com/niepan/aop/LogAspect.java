package com.niepan.aop;

import com.alibaba.fastjson.JSONObject;
import com.niepan.mapper.OperateLogMapper;
import com.niepan.pojo.OperateLog;
import com.niepan.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.niepan.anno.Log)")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {
        // 操作日ID --当前登录员工
        // 获取请求头中的令牌,解析
        final String jwt = request.getHeader("token");
        final Claims claims = JwtUtils.parseJWT(jwt);
        final Integer operateUser = (Integer) claims.get("id");
        // 操作时间
        final LocalDateTime operateTime = LocalDateTime.now();
        // 操作类名
        final String className = point.getTarget().getClass().getName();
        // 操作方法名
        final String methodName = point.getSignature().getName();
        // 操作方法参数
        final long s = System.currentTimeMillis();
        final Object[] args = point.getArgs();
        final long e = System.currentTimeMillis();
        final String methodParams = Arrays.toString(args);
        //调用原始方 法获取返回值
        final Object res = point.proceed();
        // 方法返回值
        final String returnValue = JSONObject.toJSONString(res);
        // 操作耗时
        long costTime = e-s;


        // 记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        return res;
    }
}
