package com.niepan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.niepan.mapper.EmpMapper;
import com.niepan.pojo.Emp;
import com.niepan.pojo.PageBean;
import com.niepan.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    /*
    * 查询员工
    * */
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.执行查询
        final Page<Emp> p = (Page<Emp>) empMapper.list(name, gender, begin, end);
        //3.封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    /*
    * 删除员工
    * */
    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /*
    * 新建员工
    * */
    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    /*
    * 通过ID获取员工
    * */
    @Override
    public Emp getEmpById(String id) {
        return empMapper.getEmpById(id);
    }

    /*
    * 编辑员工
    * */
    @Override
    public void edit(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.edit(emp);
    }

    /*
    * 员工登录
    * */
    @Override
    public Emp login(Emp emp) {
        return empMapper.getEmpByUsernameAndPassword(emp);
    }
}
