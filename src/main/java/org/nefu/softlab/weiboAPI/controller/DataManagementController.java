package org.nefu.softlab.weiboAPI.controller;

import lombok.extern.slf4j.Slf4j;
import org.nefu.softlab.weiboAPI.service.DataCleanupService;
import org.nefu.softlab.weiboAPI.service.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/data")
public class DataManagementController {
    
    @Autowired
    private DataCleanupService dataCleanupService;
    
    @Autowired
    private SampleDataService sampleDataService;
    
    @PostMapping("/cleanup")
    public ResponseEntity<String> cleanupData() {
        try {
            dataCleanupService.cleanupAllData();
            return ResponseEntity.ok("数据清理成功");
        } catch (Exception e) {
            log.error("数据清理失败", e);
            return ResponseEntity.status(500).body("数据清理失败: " + e.getMessage());
        }
    }
    
    @PostMapping("/generate-sample")
    public ResponseEntity<String> generateSampleData() {
        try {
            sampleDataService.generateSampleData();
            return ResponseEntity.ok("示例数据生成成功");
        } catch (Exception e) {
            log.error("示例数据生成失败", e);
            return ResponseEntity.status(500).body("示例数据生成失败: " + e.getMessage());
        }
    }
} 