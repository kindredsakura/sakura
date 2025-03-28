package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * 数据量统计控制器
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/api/statistics")
@RestController
public class StatisticsController {
    
    @Autowired
    private StatisticsService statisticsService;
    
    @GetMapping("/list")
    public List<Map<String, Object>> getStatistics() {
        return statisticsService.getStatistics();
    }
    
    @GetMapping("/total")
    public Map<String, Object> getTotalStatistics() {
        return statisticsService.getTotalStatistics();
    }
    
    @GetMapping("/server/{serverId}")
    public Map<String, Object> getServerStatistics(@PathVariable String serverId) {
        return statisticsService.getServerStatistics(serverId);
    }
} 