package com.challenge.challenge.repository;

import com.challenge.challenge.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    @Query("FROM Post p where p.title like %:title% and p.description like %:description%")
    public List<Post> searchPost(String title,String description);
}
