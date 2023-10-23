package com.challenge.challenge.repository;

import com.challenge.challenge.model.EStatus;
import com.challenge.challenge.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, String> {
    int countAllByStatus(EStatus status);

    @Query("from likes l where l.post.id=:postId and l.status=:status")
    List<Likes> findAllByStatus(EStatus status, String postId);

    @Query("from likes l where l.userCore.id=:userid and l.post.id=:postId")
    Likes getLikeByUserAndPost(String userid, String postId);

}
