package com.challenge.challenge.service;

import com.challenge.challenge.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Override
    public List<Comment> getAllComment() {
        return null;
    }

    @Override
    public Comment getCommentById(String postId) {
        return null;
    }

    @Override
    public Comment createComment(Comment post) {
        return null;
    }

    @Override
    public Comment updateComment(Comment post) {
        return null;
    }

    @Override
    public boolean deleteComment(String postId) {
        return false;
    }

    @Override
    public List<Comment> searchComment(String title) {
        return null;
    }
}
