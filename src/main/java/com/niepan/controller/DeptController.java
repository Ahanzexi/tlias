package com.niepan.controller;

import com.niepan.pojo.Dept;
import com.niepan.pojo.Result;
import com.niepan.service.DeptService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
部门管理
*/
@Slf4j
@RestController
public class DeptController {
    // 注解替换
    // private static Logger log = LoggerFactory.getLogger(DeptController.class);
    // @RequestMapping(value = "/depts",method = RequestMethod.GET)
    @Autowired
    private DeptService deptService;
    @GetMapping("/depts")
    public Result list(){
        log.info("查询全部部门数据");
        // 调用service查询全部部门
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
}
