package org.nefu.softlab.weiboAPI.service;

import org.nefu.softlab.weiboAPI.entity.WeiboUser;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;

import java.util.List;

/**
 * 微博用户服务接口
 */
public interface WeiboUserService {
    
    /**
     * 获取TOP50的微博大咖
     * @return 微博用户列表
     */
    List<WeiboUser> getFamousUsers();

    /**
     * 获取微博用户数
     * @return 用户数量
     */
    Long selectUserCount();

    /**
     * 根据昵称查询用户ID
     * @param uname 用户昵称
     * @return 用户ID
     */
    String selectUidByName(String uname);

    /**
     * 根据昵称模糊查询用户ID列表
     * @param vo 查询条件
     * @return 用户ID列表
     */
    List<String> selectUidsbyName(RecordsSelectVo vo);

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
    int insert(WeiboUser record);

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insertSelective(WeiboUser record);

    /**
     * 根据主键查询记录
     * @param uid 用户ID
     * @return 记录对象
     */
    WeiboUser selectByPrimaryKey(String uid);

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(WeiboUser record);

    /**
     * 更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(WeiboUser record);
} 