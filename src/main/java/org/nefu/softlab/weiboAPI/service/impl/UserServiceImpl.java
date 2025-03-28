package org.nefu.softlab.weiboAPI.service.impl;

import org.nefu.softlab.weiboAPI.dao.UserMapper;
import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.entity.User;
import org.nefu.softlab.weiboAPI.service.UserService;
import org.nefu.softlab.weiboAPI.utils.MD5Util;
import org.nefu.softlab.weiboAPI.utils.TokenUtil;
import org.nefu.softlab.weiboAPI.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUsernameAndPasswd(User user) {
        // TODO: 实现用户登录逻辑
        return null;
    }

    @Override
    public User selectUserByUsername(String nickname) {
        return userMapper.selectUserByUsername(nickname);
    }

    @Override
    public User selectUserByToken(String token) {
        return userMapper.selectUserByToken(token);
    }

    @Override
    public int deleteByPrimaryKey(String uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public Log getLastLog(User user) {
        return userMapper.getLastLog(user);
    }

    @Override
    public Map addLoginRecord(User user, Log newLogRecord, Log lastLogRecord) {
        // 生成新的token
        String token = TokenUtil.generateToken();
        user.setToken(token);
        // 更新用户token
        if (userMapper.updateByPrimaryKeySelective(user) > 0) {
            // 添加登录记录
            if (userMapper.addLoginRecord(newLogRecord) > 0) {
                Map<String, Object> returnData = new HashMap<>();
                returnData.put("token", token);
                returnData.put("uid", user.getUid());
                returnData.put("nickname", user.getNickname());
                return returnData;
            }
        }
        return null;
    }

    @Override
    public boolean addUserRecord(User user) {
        // TODO: 实现用户注册逻辑
        return false;
    }

    @Override
    public boolean setNewPasswd(User user, String newpasswd) {
        user.setPasswd(MD5Util.MD5(newpasswd));
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public boolean deleteLogin(User user) {
        user.setToken(null);
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }
} 