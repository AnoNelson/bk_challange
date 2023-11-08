package com.challenge.challenge.controller;

import com.challenge.challenge.constants.ServerRoutes;
import com.challenge.challenge.model.Post;
import com.challenge.challenge.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
public class PostController {
    final PostService postService;

    @GetMapping(ServerRoutes.POST_GET)
    public ResponseEntity<?> getAllPost() {
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }

    @GetMapping(ServerRoutes.POST_GET_BY_ID)
    public ResponseEntity<?> getAllPostById(@RequestParam(required = true) String id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PostMapping(ServerRoutes.POST)
    public ResponseEntity<?> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    @DeleteMapping(ServerRoutes.POST_DELETE)
    public ResponseEntity<?> DeletePost(@RequestParam(required = true) String id) {
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }

    @PutMapping(ServerRoutes.POST_UPDATE)
    public ResponseEntity<?> updatePost(@RequestBody @Valid Post post) {
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
    }

    @GetMapping(ServerRoutes.POST_SEARCH)
    public ResponseEntity<?> searchPostByTitleAndDescription(@RequestParam(required = true) String title, @RequestParam(required = true) String description) {
        return new ResponseEntity<>(postService.searchPost(title, description), HttpStatus.OK);
    }
}
