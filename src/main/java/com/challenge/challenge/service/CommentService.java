package com.challenge.challenge.service;


import com.challenge.challenge.model.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getAllComment();
    public Comment getCommentById(String postId);
    public Comment createComment(Comment post);
    public Comment updateComment(Comment post);
    public boolean deleteComment(String postId);
    public List<Comment> searchComment(String title);
}
