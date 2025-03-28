package org.nefu.softlab.weiboAPI.service.impl;

import org.nefu.softlab.weiboAPI.dao.DailyRecordMapper;
import org.nefu.softlab.weiboAPI.entity.DailyRecord;
import org.nefu.softlab.weiboAPI.service.DailyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 每日记录服务实现类
 */
@Service
public class DailyRecordServiceImpl implements DailyRecordService {

    @Autowired
    private DailyRecordMapper dailyRecordMapper;

    @Override
    public List<DailyRecord> getLastSevenDayRecord() {
        return dailyRecordMapper.getLastSevenDayRecord();
    }

    @Override
    public List<DailyRecord> getLastDayRecord() {
        return dailyRecordMapper.getLastDayRecord();
    }

    @Override
    public int deleteByPrimaryKey(String rid) {
        return dailyRecordMapper.deleteByPrimaryKey(rid);
    }

    @Override
    public int insert(DailyRecord record) {
        return dailyRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(DailyRecord record) {
        return dailyRecordMapper.insertSelective(record);
    }

    @Override
    public DailyRecord selectByPrimaryKey(String rid) {
        return dailyRecordMapper.selectByPrimaryKey(rid);
    }

    @Override
    public int updateByPrimaryKeySelective(DailyRecord record) {
        return dailyRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(DailyRecord record) {
        return dailyRecordMapper.updateByPrimaryKey(record);
    }
} 