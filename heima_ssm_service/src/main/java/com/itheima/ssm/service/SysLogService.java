package com.itheima.ssm.service;

import com.itheima.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {

    public void save(SysLog sysLog) throws Exception;

    public List<SysLog> findAll() throws Exception;
}
