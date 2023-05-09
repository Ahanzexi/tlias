package com.niepan.service;

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
}
