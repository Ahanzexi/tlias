package com.niepan.service;

import com.niepan.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> list();

    void deleteById(int id);

    void add(Dept dept);

    Dept getDeptById(int id);

    void edit(Dept dept);
}
