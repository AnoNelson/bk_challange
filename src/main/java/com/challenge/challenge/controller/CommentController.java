package com.challenge.challenge.controller;

import com.challenge.challenge.constants.ServerRoutes;
import com.challenge.challenge.dto.CommentDto;
import com.challenge.challenge.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping(ServerRoutes.COMMENT)
    public ResponseEntity<?> createComment(@RequestBody @Valid CommentDto dto){
        return new ResponseEntity<>(service.createComment(dto), HttpStatus.CREATED);
    }
}
