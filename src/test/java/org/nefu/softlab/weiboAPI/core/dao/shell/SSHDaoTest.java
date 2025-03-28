package org.nefu.softlab.weiboAPI.core.dao.shell;

import ch.ethz.ssh2.Connection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nefu.softlab.weiboAPI.component.connectionPool.SSHPool;
import org.nefu.softlab.weiboAPI.dao.shell.SSHDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * SSHDao测试类
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SSHDaoTest {

    @Autowired
    private SSHDao sshDao;

    @Autowired
    private SSHPool sshPool;

    @Test
    public void test() {
        List<Connection> connections = sshPool.getConnection();
        connections.forEach(connection -> {
            System.out.println("IP : " + sshDao.getIP(connection));
            System.out.println("Total Memory : " + sshDao.getTotalMemory(connection));
            System.out.println("Available Memory : " + sshDao.getAvailableMemory(connection));
            System.out.println("Used Memory : " + sshDao.getUsedMemory(connection));
            System.out.println("Used Disk : " + sshDao.getUsedDisk(connection));
        });
        sshPool.returnConnection(connections);
    }
}
