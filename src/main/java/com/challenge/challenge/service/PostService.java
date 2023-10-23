package com.challenge.challenge.service;

import com.challenge.challenge.dto.PostResponse;
import com.challenge.challenge.model.Post;

import java.util.List;

public interface PostService {
    public List<PostResponse> getAllPost();
    public Post getPostById(String postId);
    public Post createPost(Post post);
    public Post updatePost(Post post);
    public boolean deletePost(String postId);
    public List<Post> searchPost(String title,String description);
}
