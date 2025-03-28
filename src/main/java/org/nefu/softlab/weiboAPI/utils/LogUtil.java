package org.nefu.softlab.weiboAPI.utils;

import org.nefu.softlab.weiboAPI.entity.Log;
import org.nefu.softlab.weiboAPI.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**

 *
 * Util for User Login Log
 */
public class LogUtil {

    /**
     * 根据请求的参数获取登录记录
     * @param request
     * @return
     */
    public static Log getLog(HttpServletRequest request, Log lastLog) {
        Log log = new Log();
        // 设置基本参数
        log.setLid(UUIDUtil.getRandomID());   // id
        log.setLoginTime(LocalDateTime.now()); // 本次登录的时间
        // 装载登录的统计信息
        int count = lastLog == null ? 1 : lastLog.getCount() + 1;
        log.setCount(count);    // 登录次数
        log.setLastLoginTime(lastLog == null ? null : lastLog.getLoginTime());   // 上条登录记录时间
        log.setLastLoginIp(lastLog == null ? null : lastLog.getIp());   // 上条登录记录IP
        // 装载本次登陆的信息
        log.setUserAgent(request.getHeader("User-Agent"));  // 浏览器
        log.setIp(request.getRemoteAddr()); // IP
        log.setHostname(request.getRemoteHost());   // 客户端主机名
        return log;
    }

    /**
     * 以合理的方式返回需要打印的用户信息
     * @return
     */
    public static String getUserInfo(User user) {
        return String.format("{ uid : %s , nickname : %s }", user.getUid(), user.getNickname());
    }
} 