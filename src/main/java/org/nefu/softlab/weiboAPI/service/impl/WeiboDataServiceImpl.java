package org.nefu.softlab.weiboAPI.service.impl;

import com.github.pagehelper.PageInfo;
import org.nefu.softlab.weiboAPI.dao.WeiboDataMapper;
import org.nefu.softlab.weiboAPI.entity.WeiboData;
import org.nefu.softlab.weiboAPI.entity.WeiboUser;
import org.nefu.softlab.weiboAPI.service.WeiboDataService;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;
import org.nefu.softlab.weiboAPI.vo.WeiboRecordsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微博数据服务实现类
 */
@Service
public class WeiboDataServiceImpl implements WeiboDataService {

    @Autowired
    private WeiboDataMapper weiboDataMapper;

    @Override
    public List<WeiboData> selectRecords(HashMap condition) {
        return weiboDataMapper.selectRecords(condition);
    }

    @Override
    public int deleteByPrimaryKey(String wid) {
        return weiboDataMapper.deleteByPrimaryKey(wid);
    }

    @Override
    public int insert(WeiboData record) {
        return weiboDataMapper.insert(record);
    }

    @Override
    public int insertSelective(WeiboData record) {
        return weiboDataMapper.insertSelective(record);
    }

    @Override
    public WeiboData selectByPrimaryKey(String wid) {
        return weiboDataMapper.selectByPrimaryKey(wid);
    }

    @Override
    public int updateByPrimaryKeySelective(WeiboData record) {
        return weiboDataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(WeiboData record) {
        return weiboDataMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(WeiboData record) {
        return weiboDataMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<WeiboUser> getFamousSinaUser() {
        return weiboDataMapper.getFamousSinaUser();
    }

    @Override
    public Map<String, Object> getRecordsNumber(Boolean user, Boolean weibo) {
        return weiboDataMapper.getRecordsNumber(user, weibo);
    }

    @Override
    public List<String> getUids(RecordsSelectVo vo) {
        return weiboDataMapper.getUids(vo);
    }

    @Override
    public PageInfo<WeiboData> getSpecificRecords(RecordsSelectVo vo) {
        return weiboDataMapper.getSpecificRecords(vo);
    }

    @Override
    public String getUid(String uid) {
        return weiboDataMapper.getUid(uid);
    }

    @Override
    public List<WeiboRecordsVo> getRecords(RecordsSelectVo recordsSelectVo) {
        return weiboDataMapper.getRecords(recordsSelectVo);
    }
} 