package org.nefu.softlab.weiboAPI.service;

import java.util.List;
import java.util.Map;

/**
 * 爬虫监控服务接口
 */
public interface SpiderService {

    /**
     * 获取IP池的相关信息
     * @return IP池信息
     */
    Map<String, Object> getIppoolData();

    /**
     * 获取爬虫的状态信息
     * @return 爬虫状态信息
     */
    Map<String, Object> getStatus();

    /**
     * 获取七天内的数据量
     * @return 七天数据量列表
     */
    List<Map<String, Object>> getSevenday();

    /**
     * 获取内存占用情况
     * @return 内存占用信息列表
     */
    List<Map<String, Object>> getMemoryStatus();

    /**
     * 获取当日数据的增量
     * @return 当日数据增量列表
     */
    List<Map<String, Object>> getTodayIncreasement();
} 