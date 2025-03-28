package org.nefu.softlab.weiboAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动类
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class APIApplication {

    public static void main(String [] args) {
        SpringApplication.run(APIApplication.class, args);
    }

}
