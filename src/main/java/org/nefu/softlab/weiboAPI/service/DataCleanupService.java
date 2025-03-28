package org.nefu.softlab.weiboAPI.service;

import lombok.extern.slf4j.Slf4j;
import org.nefu.softlab.weiboAPI.repository.UserRepository;
import org.nefu.softlab.weiboAPI.repository.PostRepository;
import org.nefu.softlab.weiboAPI.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class DataCleanupService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Transactional
    public void cleanupAllData() {
        log.info("开始清理所有数据...");
        commentRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();
        log.info("数据清理完成");
    }
} 