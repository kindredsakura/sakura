package org.nefu.softlab.weiboAPI.service.impl;

import org.nefu.softlab.weiboAPI.dao.WeiboUserMapper;
import org.nefu.softlab.weiboAPI.entity.WeiboUser;
import org.nefu.softlab.weiboAPI.service.WeiboUserService;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微博用户服务实现类
 */
@Service
public class WeiboUserServiceImpl implements WeiboUserService {

    @Autowired
    private WeiboUserMapper weiboUserMapper;

    @Override
    public List<WeiboUser> getFamousUsers() {
        return weiboUserMapper.getFamousUsers();
    }

    @Override
    public Long selectUserCount() {
        return weiboUserMapper.selectUserCount();
    }

    @Override
    public String selectUidByName(String uname) {
        return weiboUserMapper.selectUidByName(uname);
    }

    @Override
    public List<String> selectUidsbyName(RecordsSelectVo vo) {
        return weiboUserMapper.selectUidsbyName(vo);
    }

    @Override
    public int deleteByPrimaryKey(String uid) {
        return weiboUserMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(WeiboUser record) {
        return weiboUserMapper.insert(record);
    }

    @Override
    public int insertSelective(WeiboUser record) {
        return weiboUserMapper.insertSelective(record);
    }

    @Override
    public WeiboUser selectByPrimaryKey(String uid) {
        return weiboUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(WeiboUser record) {
        return weiboUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(WeiboUser record) {
        return weiboUserMapper.updateByPrimaryKey(record);
    }
}