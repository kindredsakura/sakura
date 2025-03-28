package org.nefu.softlab.weiboAPI;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionTest {
    
    @Test
    public void testConnection() {
        String url = "jdbc:mysql://127.0.0.1:3307/g_nlp?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "CJQ20181003";
        
        try {
            System.out.println("正在尝试连接数据库...");
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("数据库连接成功！");
            conn.close();
        } catch (Exception e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
    }
} 