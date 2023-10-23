package com.challenge.challenge.dto;

import com.challenge.challenge.model.Comment;
import com.challenge.challenge.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentDto {
    private String userId;
    private String postId;
    private String comment;
}
