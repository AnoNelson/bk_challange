package com.challenge.challenge.service;

import com.challenge.challenge.dto.LikeDto;
import com.challenge.challenge.model.Like;

import java.util.List;

public interface LikeService {
    public int countLikes();
    public boolean createLike(LikeDto post);
    public boolean updateLike(String postId,String userId);
}
