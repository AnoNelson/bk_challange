package com.challenge.challenge.service;


import com.challenge.challenge.dto.CommentDto;
import com.challenge.challenge.model.Comment;

import java.util.List;

public interface CommentService {
    public int countComment(String postId);
    public List<Comment> getCommentById(String postId);
    public boolean createComment(CommentDto post);
}
