package org.nefu.softlab.weiboAPI.service;

import org.nefu.softlab.weiboAPI.entity.Log;

/**
 * 日志服务接口
 */
public interface LogService {
    
    /**
     * 根据主键删除记录
     * @param logid 日志ID
     * @return 影响的行数
     */
    int deleteByPrimaryKey(String logid);

    /**
     * 插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insert(Log record);

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insertSelective(Log record);

    /**
     * 根据主键查询记录
     * @param logid 日志ID
     * @return 记录对象
     */
    Log selectByPrimaryKey(String logid);

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(Log record);

    /**
     * 更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(Log record);
} 