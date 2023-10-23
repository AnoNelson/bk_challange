package com.challenge.challenge.repository;

import com.challenge.challenge.model.Comment;
import com.challenge.challenge.model.EStatus;
import com.challenge.challenge.model.UserCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query("from Comment c where c.post.id=:postId")
    List<Comment> findAllByPostId(String postId);
}
