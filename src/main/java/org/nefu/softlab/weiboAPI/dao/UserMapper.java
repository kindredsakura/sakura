package org.nefu.softlab.weiboAPI.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.nefu.softlab.weiboAPI.dao.provider.UserSqlProvider;
import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问接口
 */
@Mapper
@Repository
public interface UserMapper {

    // write

    /**
     * 根据用户名和密码查询用户
     * @return user/null
     */
    @Select("SELECT * FROM `t_user` WHERE `username`=#{username} AND `passwd`=MD5(#{passwd})")
    User selectUserByUsernameAndPasswd(User user);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Select("SELECT * FROM `t_user` WHERE `username`=#{username}")
    User selectUserByUsername(String username);

    /**
     * 根据token获取用户
     * @param token
     * @return
     */
    @Select("SELECT * FROM `t_user` WHERE `token`=#{token} AND `lastLogin`>DATE_SUB(NOW(),INTERVAL 24 HOUR)")
    User selectUserByToken(String token);

    /**
     * 获取用户最后一条登录记录
     * @param user 用户对象
     * @return 登录记录
     */
    @Select("SELECT * FROM `t_log` WHERE `uid`=#{uid} ORDER BY `timestamp` DESC LIMIT 1")
    Log getLastLog(User user);

    /**
     * 添加登录记录
     * @param log 登录记录
     * @return 影响的行数
     */
    @Insert("INSERT INTO `t_log` (`logid`, `uid`, `timestamp`, `count`, `lastLogin`, `userAgent`, `ip`, `hostname`) " +
            "VALUES (#{logid}, #{uid}, #{timestamp}, #{count}, #{lastlogin}, #{useragent}, #{ip}, #{hostname})")
    int addLoginRecord(Log log);

    // auto generated
    @Delete({
        "delete from t_user",
        "where uid = #{uid,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String uid);

    @Insert({
        "insert into t_user (uid, username, ",
        "passwd, token, lastLogin)",
        "values (#{uid,jdbcType=CHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{passwd,jdbcType=CHAR}, #{token,jdbcType=CHAR}, #{lastlogin,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "uid, username, passwd, token, lastLogin",
        "from t_user",
        "where uid = #{uid,jdbcType=CHAR}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.CHAR, id=true),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="passwd", property="passwd", jdbcType=JdbcType.CHAR),
        @Result(column="token", property="token", jdbcType=JdbcType.CHAR),
        @Result(column="lastLogin", property="lastlogin", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(String uid);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update t_user",
        "set username = #{username,jdbcType=VARCHAR},",
          "passwd = #{passwd,jdbcType=CHAR},",
          "token = #{token,jdbcType=CHAR},",
          "lastLogin = #{lastlogin,jdbcType=VARCHAR}",
        "where uid = #{uid,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(User record);
} 