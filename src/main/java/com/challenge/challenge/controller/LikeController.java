package com.challenge.challenge.controller;

import com.challenge.challenge.constants.ServerRoutes;
import com.challenge.challenge.dto.LikeDto;
import com.challenge.challenge.model.Post;
import com.challenge.challenge.service.LikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
public class LikeController {
    private final LikeService likeService;

    @PostMapping(ServerRoutes.LIKE)
    public ResponseEntity<?> createLike(@RequestBody @Valid LikeDto like) {
        return new ResponseEntity<>(likeService.createOrUpdateLike(like), HttpStatus.CREATED);
    }


}
