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
import java.util.Map;

@Slf4j
@Service
public class DataCollectionService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Transactional
    public void collectUserInfo(Map<String, Object> userInfo) {
        try {
            User user = new User();
            user.setUid(userInfo.get("uid").toString());
            user.setNickname(userInfo.get("nickname").toString());
            user.setGender(userInfo.get("gender").toString());
            user.setLocation(userInfo.get("location").toString());
            user.setDescription(userInfo.get("description").toString());
            user.setFollowersCount(Integer.parseInt(userInfo.get("followers_count").toString()));
            user.setFollowingCount(Integer.parseInt(userInfo.get("following_count").toString()));
            user.setPostsCount(Integer.parseInt(userInfo.get("posts_count").toString()));
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            
            userRepository.save(user);
            log.info("Successfully saved user info for uid: {}", user.getUid());
        } catch (Exception e) {
            log.error("Error saving user info: {}", e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public void collectPostInfo(Map<String, Object> postInfo) {
        try {
            Post post = new Post();
            post.setMid(postInfo.get("mid").toString());
            post.setUid(postInfo.get("uid").toString());
            post.setContent(postInfo.get("content").toString());
            post.setRepostCount(Integer.parseInt(postInfo.get("repost_count").toString()));
            post.setCommentCount(Integer.parseInt(postInfo.get("comment_count").toString()));
            post.setLikeCount(Integer.parseInt(postInfo.get("like_count").toString()));
            post.setCreatedAt(LocalDateTime.now());
            post.setUpdatedAt(LocalDateTime.now());
            
            User user = userRepository.findByUid(post.getUid());
            if (user != null) {
                post.setUser(user);
            }
            
            postRepository.save(post);
            log.info("Successfully saved post info for mid: {}", post.getMid());
        } catch (Exception e) {
            log.error("Error saving post info: {}", e.getMessage());
            throw e;
        }
    }
    
    @Transactional
    public void collectCommentInfo(Map<String, Object> commentInfo) {
        try {
            Comment comment = new Comment();
            comment.setCid(commentInfo.get("cid").toString());
            comment.setMid(commentInfo.get("mid").toString());
            comment.setUid(commentInfo.get("uid").toString());
            comment.setContent(commentInfo.get("content").toString());
            comment.setLikeCount(Integer.parseInt(commentInfo.get("like_count").toString()));
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUpdatedAt(LocalDateTime.now());
            
            Post post = postRepository.findByMid(comment.getMid());
            if (post != null) {
                comment.setPost(post);
            }
            
            User user = userRepository.findByUid(comment.getUid());
            if (user != null) {
                comment.setUser(user);
            }
            
            commentRepository.save(comment);
            log.info("Successfully saved comment info for cid: {}", comment.getCid());
        } catch (Exception e) {
            log.error("Error saving comment info: {}", e.getMessage());
            throw e;
        }
    }
} 