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

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getEmpById(String id) {
        return empMapper.getEmpById(id);
    }

    @Override
    public void edit(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.edit(emp);
    }
}
