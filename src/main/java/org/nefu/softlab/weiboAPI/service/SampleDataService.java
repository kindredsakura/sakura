package org.nefu.softlab.weiboAPI.service;

import lombok.extern.slf4j.Slf4j;
import org.nefu.softlab.weiboAPI.entity.User;
import org.nefu.softlab.weiboAPI.entity.Post;
import org.nefu.softlab.weiboAPI.entity.Comment;
import org.nefu.softlab.weiboAPI.repository.UserRepository;
import org.nefu.softlab.weiboAPI.repository.PostRepository;
import org.nefu.softlab.weiboAPI.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class SampleDataService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    private final Random random = new Random();
    private final String[] nicknames = {"张三", "李四", "王五", "赵六", "钱七", "孙八", "周九", "吴十"};
    private final String[] contents = {
        "今天天气真好！",
        "分享一个有趣的故事...",
        "周末去哪里玩呢？",
        "新买的手机真不错！",
        "最近在学习Spring Boot",
        "推荐一部好看的电影",
        "今天去爬山了",
        "分享美食照片"
    };
    private final String[] genders = {"男", "女"};
    
    @Transactional
    public void generateSampleData() {
        log.info("开始生成示例数据...");
        
        // 生成用户
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User();
            user.setUid("user" + i);
            user.setNickname("用户" + i);
            user.setPasswd("password" + i);
            user.setToken("token" + i);
            user.setGender(genders[random.nextInt(genders.length)]);
            user.setLocation("城市" + i);
            user.setDescription("这是一个示例用户");
            user.setFollowersCount(random.nextInt(1000));
            user.setFollowingCount(random.nextInt(500));
            user.setPostsCount(random.nextInt(100));
            user.setCreatedAt(LocalDateTime.now().minusDays(random.nextInt(365)));
            user.setUpdatedAt(LocalDateTime.now());
            users.add(user);
        }
        for (User user : users) {
            userRepository.save(user);
        }
        
        // 生成微博
        List<Post> posts = new ArrayList<>();
        for (User user : users) {
            for (int i = 1; i <= 3; i++) {
                Post post = new Post();
                post.setMid("post" + user.getUid() + "_" + i);
                post.setUid(user.getUid());
                post.setContent("这是用户" + user.getNickname() + "的第" + i + "条微博");
                post.setRepostCount(random.nextInt(100));
                post.setCommentCount(random.nextInt(50));
                post.setLikeCount(random.nextInt(200));
                post.setUser(user);
                posts.add(post);
            }
        }
        for (Post post : posts) {
            postRepository.save(post);
        }
        
        // 生成评论
        List<Comment> comments = new ArrayList<>();
        for (Post post : posts) {
            for (int i = 1; i <= 3; i++) {
                Comment comment = new Comment();
                comment.setCid("comment" + post.getMid() + "_" + i);
                comment.setMid(post.getMid());
                comment.setUid("user" + (random.nextInt(5) + 1));
                comment.setContent("这是对微博" + post.getMid() + "的第" + i + "条评论");
                comment.setPost(post);
                comments.add(comment);
            }
        }
        for (Comment comment : comments) {
            commentRepository.save(comment);
        }
        
        log.info("示例数据生成完成");
    }
} 