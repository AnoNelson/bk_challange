package com.challenge.challenge.service;

import com.challenge.challenge.dto.PostResponse;
import com.challenge.challenge.exceptions.GlobalException;
import com.challenge.challenge.exceptions.ResponseException;
import com.challenge.challenge.model.EStatus;
import com.challenge.challenge.model.Likes;
import com.challenge.challenge.model.Post;
import com.challenge.challenge.repository.LikeRepository;
import com.challenge.challenge.repository.PostRepository;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;
    private final LikeService likeService;
    private final CommentService commentService;
    @Override
    public List<PostResponse> getAllPost() {
      List<Post> posts =  repository.findAll();
      List<PostResponse> likes = posts.stream().map(post -> {
          post.setLikes(likeService.activeLikesBYPost(post.getId()));
          post.setComments(commentService.getCommentById(post.getId()));
          return PostResponse.FROM_POST(post,post.getComments().size(),post.getLikes().size());
      }).collect(Collectors.toList());
      return likes;
    }

    @Override
    public Post getPostById(String postId) {
        Post post = repository.findByIdAndStatus(postId,EStatus.ACTIVE);
        return post;
    }

    @Override
    public Post createPost(Post post) {
        try {
            return repository.save(post);
        } catch (Exception e) {
            throw new GlobalException("Exception occurred " + e.getMessage());
        }

    }


    @Override
    public Post updatePost(@NotNull Post post) {
        try {
            if (Strings.isNullOrEmpty(post.getId()))
                throw new ResponseException("Post Id is missing");
            Post oldPost = getPostById(post.getId());
            if (oldPost == null)
                throw new ResponseException("Post to be updated can not be found");
            oldPost.setTitle(post.getTitle());
            oldPost.setDescription(post.getDescription());
            return repository.save(oldPost);
        } catch (Exception e) {
            throw new GlobalException("Exception occurred " + e.getMessage());
        }
    }

    @Override
    public boolean deletePost(String postId) {
        try {
            Post post = getPostById(postId);
            if (post == null)
                throw new ResponseException("post can't be found");
            post.setStatus(EStatus.DELETED);
            repository.save(post);
            return true;
        } catch (Exception e) {
            throw new GlobalException("Exception occurred " + e.getMessage());
        }
    }

    @Override
    public List<Post> searchPost(String title,String description) {
        try {
            return repository.searchPost(title,description);
        } catch (Exception e) {
            throw new GlobalException("Exception occurred " + e.getMessage());
        }
    }
}
