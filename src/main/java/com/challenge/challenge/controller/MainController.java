package com.challenge.challenge.controller;


import com.challenge.challenge.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MainController {
    @Autowired
    private PostRepository repository;
    @GetMapping("/thread")
    public ResponseEntity<?> hello() throws InterruptedException {
        Thread.sleep(1000);
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}
