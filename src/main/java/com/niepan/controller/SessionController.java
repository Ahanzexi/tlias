package com.niepan.controller;

import com.niepan.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","zzx"));
        return Result.success();
    }
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("login_username"))
                System.out.println("login_username"+"  "+cookie.getValue());
        }
        return Result.success();
    }
    @GetMapping("/s1")
    public Result session1(HttpSession session){
        log.info("HttpSession-s1: {}",session.hashCode());
        session.setAttribute("loginUser","tom");
        return Result.success();
    }

    @GetMapping("/s2")
    public Result session2(HttpServletRequest request){
        final HttpSession session = request.getSession();
        final String loginUser = (String) session.getAttribute("loginUser");
        log.info("SessionId: {}",session.hashCode());
        log.info("HttpSession-s2: {}",loginUser);
        return Result.success();
    }
    /*
    cookies 和 session:
        优点:HTTP协议中支持的技术缺点:
        移动端APP无法使用Cookie
        不安全，用户可以自己禁用
        cookieCookie不能跨域
    session:
        服务器集群环境下无法直接使用session
    令牌:
        支持PC,移动,小程序端
        解决集群环境下的认证问题
        减轻服务器端的存储压力
        缺点:需要自己实现
    */

}
