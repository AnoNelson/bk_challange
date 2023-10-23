package com.challenge.challenge.dto;

import com.challenge.challenge.model.Comment;
import com.challenge.challenge.model.Likes;
import com.challenge.challenge.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private String id;
    private String title;
    private String description;
    private List<Comment> comments;
    private List<Likes> likes;
    private int commentCount;
    private int likesCount;

    public static PostResponse FROM_POST(Post post, int commentCount, int likesCount) {
        return new PostResponse(post.getId(), post.getTitle(), post.getDescription(), post.getComments(), post.getLikes(), commentCount, likesCount);
    }
}
