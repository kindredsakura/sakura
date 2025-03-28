package org.nefu.softlab.weiboAPI.repository;

import org.nefu.softlab.weiboAPI.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByCid(String cid);
} 