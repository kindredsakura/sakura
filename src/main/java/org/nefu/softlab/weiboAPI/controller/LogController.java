package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 日志控制器
 */
@RestController
@RequestMapping("/api/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 根据ID删除记录
     * @param logid 日志ID
     * @return 操作结果
     */
    @DeleteMapping("/{logid}")
    public int deleteByPrimaryKey(@PathVariable String logid) {
        return logService.deleteByPrimaryKey(logid);
    }

    /**
     * 插入新记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PostMapping
    public int insert(@RequestBody Log record) {
        return logService.insert(record);
    }

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PostMapping("/selective")
    public int insertSelective(@RequestBody Log record) {
        return logService.insertSelective(record);
    }

    /**
     * 根据ID查询记录
     * @param logid 日志ID
     * @return 记录对象
     */
    @GetMapping("/{logid}")
    public Log selectByPrimaryKey(@PathVariable String logid) {
        return logService.selectByPrimaryKey(logid);
    }

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PutMapping("/selective")
    public int updateByPrimaryKeySelective(@RequestBody Log record) {
        return logService.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PutMapping
    public int updateByPrimaryKey(@RequestBody Log record) {
        return logService.updateByPrimaryKey(record);
    }
} 