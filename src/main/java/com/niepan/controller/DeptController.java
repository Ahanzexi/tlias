package com.niepan.controller;

import com.niepan.pojo.Dept;
import com.niepan.pojo.Result;
import com.niepan.service.DeptService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
部门管理
*/
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    // 注解替换
    // private static Logger log = LoggerFactory.getLogger(DeptController.class);
    // @RequestMapping(value = "/depts",method = RequestMethod.GET)

    /* 1.1 部门列表查询 */
    @Autowired
    private DeptService deptService;
    @GetMapping()
    public Result list(){
        log.info("查询全部部门数据");
        // 调用service查询全部部门
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /* 1.2 删除部门 */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id)  {
        log.info("根据ID删除部门");
        deptService.deleteById(id);
        return Result.success();
    }

    /*  1.3 新增部门 */
    @PostMapping()
    public Result add(@RequestBody Dept dept){
        log.info("新增部门");
        deptService.add(dept);
        return Result.success();
    }

    /* 1.4 根据ID查询 */
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable int id){
        final Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }

    /* 1.5 修改部门 */
    @PutMapping()
    public Result edit(@RequestBody Dept dept){
        deptService.edit(dept);
        return Result.success();
    }
}
