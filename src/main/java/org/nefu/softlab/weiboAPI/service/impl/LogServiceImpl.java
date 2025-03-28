package org.nefu.softlab.weiboAPI.service.impl;

import org.nefu.softlab.weiboAPI.dao.LogMapper;
import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志服务实现类
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int deleteByPrimaryKey(String logid) {
        return logMapper.deleteByPrimaryKey(logid);
    }

    @Override
    public int insert(Log record) {
        return logMapper.insert(record);
    }

    @Override
    public int insertSelective(Log record) {
        return logMapper.insertSelective(record);
    }

    @Override
    public Log selectByPrimaryKey(String logid) {
        return logMapper.selectByPrimaryKey(logid);
    }

    @Override
    public int updateByPrimaryKeySelective(Log record) {
        return logMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Log record) {
        return logMapper.updateByPrimaryKey(record);
    }
} 