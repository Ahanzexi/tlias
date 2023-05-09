package com.niepan.controller;

import com.niepan.pojo.Emp;
import com.niepan.pojo.Result;
import com.niepan.service.EmpService;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        final boolean login = empService.login(emp);
        if (login){
            // 登录成功
            return Result.success();
        }else{
            // 登录失败
            return Result.error("用户名或密码错误");
        }

    }
}
