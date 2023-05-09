package com.niepan.controller;

import com.github.pagehelper.Page;
import com.niepan.pojo.Emp;
import com.niepan.pojo.PageBean;
import com.niepan.pojo.Result;
import com.niepan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
员工管理
* */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /*
    * 2.1 员工列表查询
    * */
    @GetMapping()
    public Result page(
            @RequestParam(defaultValue = "1") Integer page
            , @RequestParam(defaultValue = "10") Integer pageSize
            ,  String name
            ,  Short gender
            , @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin
            , @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询,参数:{},{},{},{},{},{}", page, pageSize,name,gender,begin,end);
        // 调用service分页查询
        PageBean pageBean = empService.page(page, pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    /*
    * 2.2 删除员工
    * */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
       empService.delete(ids);
       return Result.success();
    }

    /*
    * 2.3 添加员工
    * */
    @PostMapping()
    public Result add(@RequestBody Emp emp){
        empService.add(emp);
        return Result.success();
    }

    /*
    * 2.4 根据ID查询
    * */
    @GetMapping("/{id}")
    public Result getEmpById(@PathVariable String id){
        Emp emp = empService.getEmpById(id);
        return Result.success(emp);
    }

    /*
    * 2.5 修改员工
    * */
    @PutMapping()
    public Result edit(@RequestBody Emp emp){
        empService.edit(emp);
        return Result.success();
    }
}
