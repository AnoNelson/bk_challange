package com.challenge.challenge.service;

import com.challenge.challenge.dto.LikeDto;
import com.challenge.challenge.exceptions.GlobalException;
import com.challenge.challenge.model.EStatus;
import com.challenge.challenge.model.Like;
import com.challenge.challenge.model.Post;
import com.challenge.challenge.model.UserCore;
import com.challenge.challenge.repository.LikeRepository;
import com.challenge.challenge.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository repository;
    private final PostService postService;
    private final UserCoreService userCoreService;

    @Override
    public int countLikes() {
        return repository.countAllByStatus(EStatus.ACTIVE.name());
    }

    @Override
    public boolean createLike(LikeDto likeDto) {
        try {
            UserCore user = userCoreService.findUserById(likeDto.getUserId());
            Post post = postService.getPostById(likeDto.getPostId());
            if (user == null || post == null)
                throw new GlobalException("Either the user or Post doesn't exist");
            Like like = new Like();
            like.setPost(post);
            like.setUserCore(user);
            repository.save(like);
            return true;
        } catch (Exception e) {
            throw new GlobalException("Error Occurred " + e.getMessage());
        }
    }

    @Override
    public boolean updateLike(String postId,String userId) {
        return false;
    }
}
