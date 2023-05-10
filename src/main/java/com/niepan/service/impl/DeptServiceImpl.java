package com.niepan.service.impl;

import com.niepan.mapper.DeptMapper;
import com.niepan.mapper.EmpMapper;
import com.niepan.pojo.Dept;
import com.niepan.pojo.DeptLog;
import com.niepan.service.DeptLogService;
import com.niepan.service.DeptService;
import com.niepan.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;
    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) // spring事务管理注解
    @Override
    public void deleteById(int  id)  {
        try {
            deptMapper.delete(id); // 根据ID删除部门
            // 只有运行时异常可以回滚,回滚所有异常徐设置    @Transactional(rollbackFor = Exception.class)
            Integer x = 1/0;
            empMapper.deleteByDeptId(id);// 根据部门ID删除该部员工
        } finally {
            // 记录操作日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("id为 "+id+" 的部门执行了解散部门的操作");
            deptLogService.insert(deptLog);
        }


    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(int id) {
        return deptMapper.getDeptById(id);
    }

    @Override
    public void edit(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.edit(dept);
    }
}
