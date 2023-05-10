package com.niepan.service.impl;

import com.niepan.mapper.DeptLogMapper;
import com.niepan.pojo.DeptLog;
import com.niepan.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    // 新事务,而不是加入
    @Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Transactional
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
