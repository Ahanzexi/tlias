package com.niepan.service;

import com.niepan.pojo.Emp;
import com.niepan.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /*
    * 分页,筛选,查询员工
    * */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /*
    * 批量删除员工
    * */
    void delete(List<Integer> ids);

    /*
    * 新增员工
    * */
    void add(Emp emp);

    /*
    * 根据id查询员工
    * */
    Emp getEmpById(String id);

    /*
    * 修改员工
    * */
    void edit(Emp emp);

    Emp login(Emp emp);
}
