package org.nefu.softlab.weiboAPI.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts", indexes = {
    @Index(name = "idx_mid", columnList = "mid"),
    @Index(name = "idx_id", columnList = "id"),
    @Index(name = "idx_uid", columnList = "uid")
})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private String mid;
    private String uid;
    private String content;
    private Integer repostCount;
    private Integer commentCount;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
} 