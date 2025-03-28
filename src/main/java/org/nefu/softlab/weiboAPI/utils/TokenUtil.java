package org.nefu.softlab.weiboAPI.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Token工具类
 */
public class TokenUtil {
    
    /**
     * 从请求头中获取token
     * @param request HTTP请求
     * @return token字符串
     */
    public static String getToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    /**
     * 生成新的token
     * @return token字符串
     */
    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }
} 