package org.nefu.softlab.weiboAPI.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.nefu.softlab.weiboAPI.entity.Log;

/**
 * 日志SQL提供者
 */
public class LogSqlProvider {

    public String insertSelective(Log record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_user_log");
        
        if (record.getLid() != null) {
            sql.VALUES("lid", "#{lid,jdbcType=CHAR}");
        }
        
        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=CHAR}");
        }
        
        if (record.getCount() != null) {
            sql.VALUES("count", "#{count,jdbcType=INTEGER}");
        }
        
        if (record.getIp() != null) {
            sql.VALUES("ip", "#{ip,jdbcType=CHAR}");
        }
        
        if (record.getUserAgent() != null) {
            sql.VALUES("user_agent", "#{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getHostname() != null) {
            sql.VALUES("hostname", "#{hostname,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginTime() != null) {
            sql.VALUES("login_time", "#{loginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastLoginTime() != null) {
            sql.VALUES("last_login_time", "#{lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastLoginIp() != null) {
            sql.VALUES("last_login_ip", "#{lastLoginIp,jdbcType=CHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Log record) {
        SQL sql = new SQL();
        sql.UPDATE("t_user_log");
        
        if (record.getUid() != null) {
            sql.SET("uid = #{uid,jdbcType=CHAR}");
        }
        
        if (record.getCount() != null) {
            sql.SET("count = #{count,jdbcType=INTEGER}");
        }
        
        if (record.getIp() != null) {
            sql.SET("ip = #{ip,jdbcType=CHAR}");
        }
        
        if (record.getUserAgent() != null) {
            sql.SET("user_agent = #{userAgent,jdbcType=VARCHAR}");
        }
        
        if (record.getHostname() != null) {
            sql.SET("hostname = #{hostname,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginTime() != null) {
            sql.SET("login_time = #{loginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastLoginTime() != null) {
            sql.SET("last_login_time = #{lastLoginTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastLoginIp() != null) {
            sql.SET("last_login_ip = #{lastLoginIp,jdbcType=CHAR}");
        }
        
        sql.WHERE("lid = #{lid,jdbcType=CHAR}");
        
        return sql.toString();
    }
} 