package org.nefu.softlab.weiboAPI.component.connectionPool;

import ch.ethz.ssh2.Connection;
import org.nefu.softlab.weiboAPI.config.SSHConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SSH连接池
 */
@Component
public class SSHPool {

    private static final Logger logger = LoggerFactory.getLogger(SSHPool.class);
    private static final int POOL_SIZE = 10;
    private final Queue<List<Connection>> connections;
    private final Lock lock;
    private static final boolean ENABLE_SSH = false; // 添加开关控制是否启用SSH连接

    public SSHPool() {
        this.connections = new LinkedList<>();
        this.lock = new ReentrantLock(true);  // 使用公平锁
    }

    @PostConstruct
    public void init() {
        if (!ENABLE_SSH) {
            logger.info("SSH Connection Pool is disabled.");
            return;
        }
        for (int i = 0; i < POOL_SIZE; i++) {
            connections.offer(createConnection());
        }
        logger.info("SSH Connection Pool (Size : {}) Built Successfully.", connections.size());
    }

    @PreDestroy
    public void destroy() {
        if (!ENABLE_SSH) {
            return;
        }
        lock.lock();
        try {
            while (!connections.isEmpty()) {
                List<Connection> connectionList = connections.poll();
                if (connectionList != null) {
                    connectionList.forEach(Connection::close);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 创建一组新的SSH连接
     * @return 新创建的SSH连接列表
     */
    private List<Connection> createConnection() {
        if (!ENABLE_SSH) {
            return new LinkedList<>();
        }
        List<Map<String, String>> configList = SSHConfig.configList;
        List<Connection> connections = new LinkedList<>();
        configList.forEach(map -> {
            try {
                Connection connection = new Connection(map.get("hostname"));
                connection.connect();
                if (!connection.authenticateWithPassword(map.get("username"), map.get("password"))) {
                    throw new IOException("Authentication failed");
                }
                connections.add(connection);
            } catch (IOException e) {
                logger.warn("SSH Connect to server {} failed.", map.get("hostname"));
            }
        });
        return connections;
    }

    /**
     * 获取SSH连接
     * 如果当前连接池中有连接，则从池中取
     * 否则创建新连接并返回
     * @return SSH连接列表
     */
    public List<Connection> getConnection() {
        if (!ENABLE_SSH) {
            return new LinkedList<>();
        }
        lock.lock();
        try {
            if (!connections.isEmpty()) {
                return connections.poll();
            }
            return createConnection();
        } finally {
            logger.debug("One SSH connection left connection pool, left connections: {}", connections.size());
            lock.unlock();
        }
    }

    /**
     * 归还SSH连接
     * 可以直接将连接返还避免创建连接的开销
     * 但是容量不能超过预设的值
     * @param connection 要归还的SSH连接列表
     */
    public void returnConnection(List<Connection> connection) {
        if (!ENABLE_SSH || connection == null) {
            return;
        }

        lock.lock();
        try {
            if (connections.size() < POOL_SIZE) {
                connections.offer(connection);
                logger.debug("One SSH connection returned to pool, current size: {}", connections.size());
            } else {
                connection.forEach(Connection::close);
            }
        } finally {
            lock.unlock();
        }
    }
} 