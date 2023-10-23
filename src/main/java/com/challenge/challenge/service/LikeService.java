package com.challenge.challenge.service;

import com.challenge.challenge.dto.LikeDto;
import com.challenge.challenge.model.Likes;

import java.util.List;

public interface LikeService {
     int countLikes();
     boolean createOrUpdateLike(LikeDto post);
     boolean updateLike(Likes likes);
     List<Likes> activeLikesBYPost(String postId);
}
