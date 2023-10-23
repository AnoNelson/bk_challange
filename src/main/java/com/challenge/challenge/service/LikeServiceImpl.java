package com.challenge.challenge.service;

import com.challenge.challenge.dto.LikeDto;
import com.challenge.challenge.exceptions.GlobalException;
import com.challenge.challenge.model.EStatus;
import com.challenge.challenge.model.Likes;
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
    private final PostRepository postService;
    private final UserCoreService userCoreService;

    @Override
    public int countLikes() {
        return repository.countAllByStatus(EStatus.ACTIVE);
    }

    @Override
    public boolean createOrUpdateLike(LikeDto likeDto) {
        try {
            UserCore user = userCoreService.findUserById(likeDto.getUserId());
            Post post = postService.findById(likeDto.getPostId()).orElse(null);
            if (user == null || post == null)
                throw new GlobalException("Either the user or Post doesn't exist");
            Likes likes = repository.getLikeByUserAndPost(likeDto.getUserId(), likeDto.getPostId());
            if (likes == null) {
                likes = new Likes();
                likes.setPost(post);
                likes.setUserCore(user);
                repository.save(likes);
                return true;
            } else {
                return updateLike(likes);
            }
        } catch (Exception e) {
            throw new GlobalException("Error Occurred " + e.getMessage());
        }
    }

    @Override
    public boolean updateLike(Likes likes) {
        try {
            if (likes.getStatus().name().equalsIgnoreCase(EStatus.ACTIVE.name()))
                likes.setStatus(EStatus.DELETED);
            else
                likes.setStatus(EStatus.ACTIVE);
            repository.save(likes);
            return true;
        } catch (Exception e) {
            throw new GlobalException("liking the post failed");
        }
    }

    @Override
    public List<Likes> activeLikesBYPost(String postId) {
        return repository.findAllByStatus(EStatus.ACTIVE, postId);
    }
}
