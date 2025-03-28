package org.nefu.softlab.weiboAPI.dao.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.PostConstruct;

/**
 * Redis 基础 DAO 类
 */
public class BaseDao {
    
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
    
    protected ValueOperations<String, Object> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }
} 