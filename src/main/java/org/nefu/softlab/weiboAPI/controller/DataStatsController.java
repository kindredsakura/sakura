package org.nefu.softlab.weiboAPI.controller;

import lombok.extern.slf4j.Slf4j;
import org.nefu.softlab.weiboAPI.repository.UserRepository;
import org.nefu.softlab.weiboAPI.repository.PostRepository;
import org.nefu.softlab.weiboAPI.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"}, allowCredentials = "true")
public class DataStatsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        try {
            Map<String, Object> stats = new HashMap<>();
            stats.put("userCount", userRepository.count());
            stats.put("postCount", postRepository.count());
            stats.put("commentCount", commentRepository.count());
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            log.error("获取统计数据失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取统计数据失败");
        }
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok(userRepository.findAll());
        } catch (Exception e) {
            log.error("获取用户列表失败", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取用户列表失败");
        }
    }
} 