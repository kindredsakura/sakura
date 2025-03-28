package org.nefu.softlab.weiboAPI.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.nefu.softlab.weiboAPI.entity.WeiboData;
import org.nefu.softlab.weiboAPI.vo.RecordsSelectVo;
import org.apache.ibatis.annotations.Param;


import java.util.Map;

/**
 * 微博数据SQL提供者
 */
public class WeiboDataSqlProvider {

    // written

    /**
     * 根据是否存在uids构造出两种sql
     * @param condition
     * @return
     */
    public String selectRecords(Map<String, Object> condition) {
        if (condition.get("uid") != null && ((String)condition.get("uid")).trim().equals("") == false)
            return "SELECT * FROM `t_weibo_data` WHERE `uid`=#{uid} AND `time` >= #{from} AND `time` <= #{to}";
        else
            return "SELECT * FROM `t_weibo_data` WHERE `time`>=#{from} AND `time`<=#{to}";
    }

    // generated

    public String insertSelective(WeiboData record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_weibo_data");
        
        if (record.getWid() != null) {
            sql.VALUES("wid", "#{wid,jdbcType=VARCHAR}");
        }
        
        if (record.getAttu() != null) {
            sql.VALUES("attu", "#{attu,jdbcType=INTEGER}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("comment", "#{comment,jdbcType=INTEGER}");
        }
        
        if (record.getDevice() != null) {
            sql.VALUES("device", "#{device,jdbcType=VARCHAR}");
        }
        
        if (record.getTime() != null) {
            sql.VALUES("time", "#{time,jdbcType=BIGINT}");
        }
        
        if (record.getRewid() != null) {
            sql.VALUES("rewid", "#{rewid,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.VALUES("content", "#{content,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(WeiboData record) {
        SQL sql = new SQL();
        sql.UPDATE("t_weibo_data");
        
        if (record.getAttu() != null) {
            sql.SET("attu = #{attu,jdbcType=INTEGER}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{comment,jdbcType=INTEGER}");
        }
        
        if (record.getDevice() != null) {
            sql.SET("device = #{device,jdbcType=VARCHAR}");
        }
        
        if (record.getTime() != null) {
            sql.SET("time = #{time,jdbcType=BIGINT}");
        }
        
        if (record.getRewid() != null) {
            sql.SET("rewid = #{rewid,jdbcType=VARCHAR}");
        }
        
        if (record.getUid() != null) {
            sql.SET("uid = #{uid,jdbcType=VARCHAR}");
        }
        
        if (record.getContent() != null) {
            sql.SET("content = #{content,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("wid = #{wid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    public String getFamousSinaUser() {
        return "SELECT DISTINCT uid FROM t_weibo_data WHERE attu > 1000 ORDER BY attu DESC LIMIT 100";
    }

    public String getRecordsNumber(@Param("user") Boolean user, @Param("weibo") Boolean weibo) {
        SQL sql = new SQL();
        sql.SELECT("COUNT(*) as total");
        if (user) {
            sql.SELECT("COUNT(DISTINCT uid) as userCount");
        }
        if (weibo) {
            sql.SELECT("COUNT(DISTINCT wid) as weiboCount");
        }
        sql.FROM("t_weibo_data");
        return sql.toString();
    }

    public String getUids(RecordsSelectVo vo) {
        SQL sql = new SQL();
        sql.SELECT("DISTINCT uid");
        sql.FROM("t_weibo_data");
        if (vo.getUid() != null && !vo.getUid().isEmpty()) {
            sql.WHERE("uid = #{uid}");
        }
        return sql.toString();
    }

    public String getSpecificRecords(RecordsSelectVo vo) {
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("t_weibo_data");
        if (vo.getUid() != null && !vo.getUid().isEmpty()) {
            sql.WHERE("uid = #{uid}");
        }
        sql.ORDER_BY("time DESC");
        return sql.toString();
    }

    public String getUid(String uid) {
        return "SELECT uid FROM t_weibo_data WHERE uid = #{uid} LIMIT 1";
    }

    public String getRecords(RecordsSelectVo recordsSelectVo) {
        SQL sql = new SQL();
        sql.SELECT("w.*, u.nickname, u.avatar");
        sql.FROM("t_weibo_data w");
        sql.LEFT_OUTER_JOIN("t_weibo_user u ON w.uid = u.uid");
        if (recordsSelectVo.getUid() != null && !recordsSelectVo.getUid().isEmpty()) {
            sql.WHERE("w.uid = #{uid}");
        }
        sql.ORDER_BY("w.time DESC");
        return sql.toString();
    }
} 