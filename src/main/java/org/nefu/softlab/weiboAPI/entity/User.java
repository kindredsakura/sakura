package org.nefu.softlab.weiboAPI.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@Entity
@Table(name = "users", indexes = {
    @Index(name = "idx_uid", columnList = "uid"),
    @Index(name = "idx_post_id", columnList = "post_id"),
    @Index(name = "idx_id", columnList = "id")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private String uid;
    private String nickname;
    private String passwd;
    private String token;
    private String gender;
    private String location;
    private String description;
    private Integer followersCount;
    private Integer followingCount;
    private Integer postsCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
} 