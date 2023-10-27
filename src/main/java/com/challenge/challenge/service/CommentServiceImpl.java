package com.challenge.challenge.service;

import com.challenge.challenge.dto.CommentDto;
import com.challenge.challenge.exceptions.GlobalException;
import com.challenge.challenge.exceptions.ResponseException;
import com.challenge.challenge.model.Comment;
import com.challenge.challenge.model.Post;
import com.challenge.challenge.model.UserCore;
import com.challenge.challenge.repository.CommentRepository;
import com.challenge.challenge.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final PostRepository postService;
    private final UserCoreService userCoreService;
    private final CommentRepository repository;

    @Override
    public int countComment(String postId) {
        return repository.findAllByPostId(postId).size();
    }

    @Override
    public List<Comment> getCommentById(String commentId) {
        try {
            return repository.findAllByPostId(commentId);
        } catch (Exception e) {
            throw new GlobalException("unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public boolean createComment(CommentDto dto) {
        try {
            UserCore user = userCoreService.findUserById(dto.getUserId());
            Post post = postService.findById(dto.getPostId()).orElse(null);
            if (user == null || post == null)
                throw new ResponseException("Either the user or Post doesn't exist");
            Comment comment = new Comment();
            comment.setComment(dto.getComment());
            comment.setPost(post);
            comment.setUserCore(user);
            repository.save(comment);
            return true;
        } catch (Exception e) {
            throw new GlobalException("Exception occurred while creating comment: " + e.getMessage());
        }
    }
}
