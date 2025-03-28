package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.entity.DailyRecord;
import org.nefu.softlab.weiboAPI.service.DailyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 每日记录控制器
 */
@RestController
@RequestMapping("/api/daily-record")
public class DailyRecordController {

    @Autowired
    private DailyRecordService dailyRecordService;

    /**
     * 获取最近七天的总数据量记录
     * @return 记录列表
     */
    @GetMapping("/last-seven-days")
    public List<DailyRecord> getLastSevenDayRecord() {
        return dailyRecordService.getLastSevenDayRecord();
    }

    /**
     * 获取当前最近的当天的数据量记录
     * @return 记录列表
     */
    @GetMapping("/last-day")
    public List<DailyRecord> getLastDayRecord() {
        return dailyRecordService.getLastDayRecord();
    }

    /**
     * 根据ID删除记录
     * @param rid 记录ID
     * @return 操作结果
     */
    @DeleteMapping("/{rid}")
    public int deleteByPrimaryKey(@PathVariable String rid) {
        return dailyRecordService.deleteByPrimaryKey(rid);
    }

    /**
     * 插入新记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PostMapping
    public int insert(@RequestBody DailyRecord record) {
        return dailyRecordService.insert(record);
    }

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PostMapping("/selective")
    public int insertSelective(@RequestBody DailyRecord record) {
        return dailyRecordService.insertSelective(record);
    }

    /**
     * 根据ID查询记录
     * @param rid 记录ID
     * @return 记录对象
     */
    @GetMapping("/{rid}")
    public DailyRecord selectByPrimaryKey(@PathVariable String rid) {
        return dailyRecordService.selectByPrimaryKey(rid);
    }

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PutMapping("/selective")
    public int updateByPrimaryKeySelective(@RequestBody DailyRecord record) {
        return dailyRecordService.updateByPrimaryKeySelective(record);
    }

    /**
     * 更新记录
     * @param record 记录对象
     * @return 操作结果
     */
    @PutMapping
    public int updateByPrimaryKey(@RequestBody DailyRecord record) {
        return dailyRecordService.updateByPrimaryKey(record);
    }
} 