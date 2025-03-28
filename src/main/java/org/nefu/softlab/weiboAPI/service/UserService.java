package org.nefu.softlab.weiboAPI.service;

import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.entity.User;

import java.util.Map;

/**
 * 用户服务接口
 */
public interface UserService {
    
    /**
     * 根据用户名和密码查询用户
     * @param user 用户对象
     * @return 用户对象
     */
    User selectUserByUsernameAndPasswd(User user);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    User selectUserByUsername(String username);

    /**
     * 根据token查询用户
     * @param token 令牌
     * @return 用户对象
     */
    User selectUserByToken(String token);

    /**
     * 根据主键删除记录
     * @param uid 用户ID
     * @return 影响的行数
     */
    int deleteByPrimaryKey(String uid);

    /**
     * 插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insert(User record);

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insertSelective(User record);

    /**
     * 根据主键查询记录
     * @param uid 用户ID
     * @return 记录对象
     */
    User selectByPrimaryKey(String uid);

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(User record);

    /**
     * 获取用户最后一条登录记录
     * @param user 用户对象
     * @return 登录记录
     */
    Log getLastLog(User user);

    /**
     * 添加登录记录
     * @param user 用户对象
     * @param newLogRecord 新的登录记录
     * @param lastLogRecord 上一条登录记录
     * @return 返回数据
     */
    Map addLoginRecord(User user, Log newLogRecord, Log lastLogRecord);

    /**
     * 添加用户记录
     * @param user 用户对象
     * @return 是否成功
     */
    boolean addUserRecord(User user);

    /**
     * 设置新密码
     * @param user 用户对象
     * @param newpasswd 新密码
     * @return 是否成功
     */
    boolean setNewPasswd(User user, String newpasswd);

    /**
     * 删除登录记录
     * @param user 用户对象
     * @return 是否成功
     */
    boolean deleteLogin(User user);
} 