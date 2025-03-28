package org.nefu.softlab.weiboAPI.service;

import org.nefu.softlab.weiboAPI.entity.DailyRecord;
import java.util.List;

/**
 * 每日记录服务接口
 */
public interface DailyRecordService {
    
    /**
     * 获取最近七天的总数据量记录
     * @return 记录列表
     */
    List<DailyRecord> getLastSevenDayRecord();

    /**
     * 获取当前最近的当天的数据量记录
     * @return 记录列表
     */
    List<DailyRecord> getLastDayRecord();

    /**
     * 根据主键删除记录
     * @param rid 记录ID
     * @return 影响的行数
     */
    int deleteByPrimaryKey(String rid);

    /**
     * 插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insert(DailyRecord record);

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insertSelective(DailyRecord record);

    /**
     * 根据主键查询记录
     * @param rid 记录ID
     * @return 记录对象
     */
    DailyRecord selectByPrimaryKey(String rid);

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(DailyRecord record);

    /**
     * 更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(DailyRecord record);
} 