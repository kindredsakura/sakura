package org.nefu.softlab.weiboAPI.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

@Configuration
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class})
public class WebConfig {
    
    @Bean
    public HealthIndicator redisHealthIndicator() {
        return () -> Health.up().build();
    }
} 