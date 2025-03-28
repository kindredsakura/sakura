package org.nefu.softlab.weiboAPI.service;

import com.github.pagehelper.PageInfo;
import org.nefu.softlab.weiboAPI.entity.WeiboData;
import org.nefu.softlab.weiboAPI.entity.WeiboUser;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;
import org.nefu.softlab.weiboAPI.vo.WeiboRecordsVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微博数据服务接口
 */
public interface WeiboDataService {
    
    /**
     * 获取指定的微博记录
     * @param condition 查询条件
     * @return 微博记录列表
     */
    List<WeiboData> selectRecords(HashMap condition);

    /**
     * 根据主键删除记录
     * @param wid 微博ID
     * @return 影响的行数
     */
    int deleteByPrimaryKey(String wid);

    /**
     * 插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insert(WeiboData record);

    /**
     * 选择性插入记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int insertSelective(WeiboData record);

    /**
     * 根据主键查询记录
     * @param wid 微博ID
     * @return 记录对象
     */
    WeiboData selectByPrimaryKey(String wid);

    /**
     * 选择性更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(WeiboData record);

    /**
     * 更新记录（包含BLOB字段）
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKeyWithBLOBs(WeiboData record);

    /**
     * 更新记录
     * @param record 记录对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(WeiboData record);

    /**
     * 获取微博大咖的业务逻辑
     * @return 微博大咖列表
     */
    List<WeiboUser> getFamousSinaUser();

    /**
     * 获取默认的微博数和用户数的业务逻辑
     * @param user 是否包含用户数
     * @param weibo 是否包含微博数
     * @return 统计信息
     */
    Map<String, Object> getRecordsNumber(Boolean user, Boolean weibo);

    /**
     * 获取用户ID列表
     * @param vo 查询条件
     * @return 用户ID列表
     */
    List<String> getUids(RecordsSelectVo vo);

    /**
     * 检索指定条件下的数据的业务逻辑
     * @param vo 查询条件
     * @return 分页的微博数据
     */
    PageInfo<WeiboData> getSpecificRecords(RecordsSelectVo vo);

    /**
     * 获取用户ID
     * @param uid 用户ID
     * @return 用户ID
     */
    String getUid(String uid);

    /**
     * 根据条件查询微博记录
     * @param recordsSelectVo 查询条件
     * @return 微博记录列表
     */
    List<WeiboRecordsVo> getRecords(RecordsSelectVo recordsSelectVo);
} 