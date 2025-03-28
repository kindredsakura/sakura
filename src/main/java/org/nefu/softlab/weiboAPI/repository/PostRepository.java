package org.nefu.softlab.weiboAPI.repository;

import org.nefu.softlab.weiboAPI.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByMid(String mid);
} 