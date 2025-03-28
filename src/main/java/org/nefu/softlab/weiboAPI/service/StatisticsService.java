package org.nefu.softlab.weiboAPI.service;

import java.util.List;
import java.util.Map;

/**
 * 数据量统计服务接口
 */
public interface StatisticsService {

    /**
     * 获取数据统计信息
     * @return 数据统计信息列表
     */
    List<Map<String, Object>> getStatistics();

    /**
     * 获取合计的数据统计信息
     * @return 合计的数据统计信息
     */
    Map<String, Object> getTotalStatistics();

    /**
     * 获取指定服务器的数据统计信息
     * @param serverId 服务器ID
     * @return 指定服务器的数据统计信息
     */
    Map<String, Object> getServerStatistics(String serverId);
} 