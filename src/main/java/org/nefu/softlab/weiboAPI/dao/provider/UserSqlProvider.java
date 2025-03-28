package org.nefu.softlab.weiboAPI.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.nefu.softlab.weiboAPI.entity.User;

/**
 * 用户SQL提供者
 */
public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("users");
        
        if (record.getUid() != null) {
            sql.VALUES("uid", "#{uid,jdbcType=VARCHAR}");
        }
        if (record.getNickname() != null) {
            sql.VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }
        if (record.getPasswd() != null) {
            sql.VALUES("passwd", "#{passwd,jdbcType=VARCHAR}");
        }
        if (record.getToken() != null) {
            sql.VALUES("token", "#{token,jdbcType=VARCHAR}");
        }
        if (record.getGender() != null) {
            sql.VALUES("gender", "#{gender,jdbcType=VARCHAR}");
        }
        if (record.getLocation() != null) {
            sql.VALUES("location", "#{location,jdbcType=VARCHAR}");
        }
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        if (record.getFollowersCount() != null) {
            sql.VALUES("followers_count", "#{followersCount,jdbcType=INTEGER}");
        }
        if (record.getFollowingCount() != null) {
            sql.VALUES("following_count", "#{followingCount,jdbcType=INTEGER}");
        }
        if (record.getPostsCount() != null) {
            sql.VALUES("posts_count", "#{postsCount,jdbcType=INTEGER}");
        }
        if (record.getCreatedAt() != null) {
            sql.VALUES("created_at", "#{createdAt,jdbcType=TIMESTAMP}");
        }
        if (record.getUpdatedAt() != null) {
            sql.VALUES("updated_at", "#{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("users");
        
        if (record.getNickname() != null) {
            sql.SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }
        if (record.getPasswd() != null) {
            sql.SET("passwd = #{passwd,jdbcType=VARCHAR}");
        }
        if (record.getToken() != null) {
            sql.SET("token = #{token,jdbcType=VARCHAR}");
        }
        if (record.getGender() != null) {
            sql.SET("gender = #{gender,jdbcType=VARCHAR}");
        }
        if (record.getLocation() != null) {
            sql.SET("location = #{location,jdbcType=VARCHAR}");
        }
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        if (record.getFollowersCount() != null) {
            sql.SET("followers_count = #{followersCount,jdbcType=INTEGER}");
        }
        if (record.getFollowingCount() != null) {
            sql.SET("following_count = #{followingCount,jdbcType=INTEGER}");
        }
        if (record.getPostsCount() != null) {
            sql.SET("posts_count = #{postsCount,jdbcType=INTEGER}");
        }
        if (record.getCreatedAt() != null) {
            sql.SET("created_at = #{createdAt,jdbcType=TIMESTAMP}");
        }
        if (record.getUpdatedAt() != null) {
            sql.SET("updated_at = #{updatedAt,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("uid = #{uid,jdbcType=VARCHAR}");
        return sql.toString();
    }
} 