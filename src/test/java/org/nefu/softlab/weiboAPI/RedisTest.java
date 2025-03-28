package org.nefu.softlab.weiboAPI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisConnection() {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set("test_key", "test_value");
        String value = (String) ops.get("test_key");
        assertEquals("test_value", value);
    }
} 