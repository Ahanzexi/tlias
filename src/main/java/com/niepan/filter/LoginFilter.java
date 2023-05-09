package com.niepan.filter;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.niepan.pojo.Result;
import com.niepan.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse resp = (HttpServletResponse) response;

        // 1.获取请求url。
        String url = req.getRequestURL().toString();
        log.info("请求的url: {}",url);
        //2.判断请求trl中是否包含login，如果包含，说明是登录操作，放行。
        if(url.contains("login")){
            log.info("登录操作,放行!");
            chain.doFilter(request,response);
            return;
        }
        //3.获取请求头中的令牌(token）
        String jwt =  req.getHeader("token");
        //4.判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回登录的信息");
            final Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象转为JSON
            final String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        //5.解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            log.info("请求头token为空,返回登录的信息");
            final Result error = Result.error("NOT_LOGIN");
            // 手动转换 对象转为JSON
            final String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }
        // 6.放行
        log.info("令牌合法,放行");
        chain.doFilter(request,response);
    }
}
