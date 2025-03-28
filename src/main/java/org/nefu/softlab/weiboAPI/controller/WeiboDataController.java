package org.nefu.softlab.weiboAPI.controller;

import org.nefu.softlab.weiboAPI.entity.WeiboData;
import org.nefu.softlab.weiboAPI.service.WeiboDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 微博数据控制器
 */
@RestController
@RequestMapping("/api/weibo")
public class WeiboDataController {

    @Autowired
    private WeiboDataService weiboDataService;

    /**
     * 获取指定的微博记录
     * @param condition 查询条件
     * @return 微博记录列表
     */
    @PostMapping("/records")
    public List<WeiboData> getRecords(@RequestBody HashMap condition) {
        return weiboDataService.selectRecords(condition);
    }

    /**
     * 删除微博记录
     * @param wid 微博ID
     * @return 影响的行数
     */
    @DeleteMapping("/{wid}")
    public int deleteWeibo(@PathVariable String wid) {
        return weiboDataService.deleteByPrimaryKey(wid);
    }

    /**
     * 新增微博记录
     * @param weiboData 微博数据对象
     * @return 影响的行数
     */
    @PostMapping
    public int addWeibo(@RequestBody WeiboData weiboData) {
        return weiboDataService.insert(weiboData);
    }

    /**
     * 选择性新增微博记录
     * @param weiboData 微博数据对象
     * @return 影响的行数
     */
    @PostMapping("/selective")
    public int addWeiboSelective(@RequestBody WeiboData weiboData) {
        return weiboDataService.insertSelective(weiboData);
    }

    /**
     * 根据ID查询微博记录
     * @param wid 微博ID
     * @return 微博数据对象
     */
    @GetMapping("/{wid}")
    public WeiboData getWeiboById(@PathVariable String wid) {
        return weiboDataService.selectByPrimaryKey(wid);
    }

    /**
     * 选择性更新微博记录
     * @param weiboData 微博数据对象
     * @return 影响的行数
     */
    @PutMapping("/selective")
    public int updateWeiboSelective(@RequestBody WeiboData weiboData) {
        return weiboDataService.updateByPrimaryKeySelective(weiboData);
    }

    /**
     * 更新微博记录（包含BLOB字段）
     * @param weiboData 微博数据对象
     * @return 影响的行数
     */
    @PutMapping("/blob")
    public int updateWeiboWithBLOBs(@RequestBody WeiboData weiboData) {
        return weiboDataService.updateByPrimaryKeyWithBLOBs(weiboData);
    }

    /**
     * 更新微博记录
     * @param weiboData 微博数据对象
     * @return 影响的行数
     */
    @PutMapping
    public int updateWeibo(@RequestBody WeiboData weiboData) {
        return weiboDataService.updateByPrimaryKey(weiboData);
    }
} 