package com.niepan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
部门管理
* */
@RestController
public class DeptController {
    @RequestMapping("/love")
    public String test(){
        return "LOVE";
    }
}
