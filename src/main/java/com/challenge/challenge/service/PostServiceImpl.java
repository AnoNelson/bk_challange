package com.challenge.challenge.service;

import com.challenge.challenge.exceptions.GlobalException;
import com.challenge.challenge.model.EStatus;
import com.challenge.challenge.model.Post;
import com.challenge.challenge.repository.PostRepository;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository repository;

    @Override
    public List<Post> getAllPost() {
        return repository.findAll();
    }

    @Override
    public Post getPostById(String postId) {
        Optional<Post> post = repository.findById(postId);
        return post.isPresent() ? post.get() : null;
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
                throw new GlobalException("Post to be updated can not be found");
            Post oldPost = getPostById(post.getId());
            if (oldPost == null)
                throw new GlobalException("Post to be updated can not be found");
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
                throw new GlobalException("post can't be found");
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
