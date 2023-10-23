package com.challenge.challenge.controller;

import com.challenge.challenge.constants.ServerRoutes;
import com.challenge.challenge.model.SimpleUserAuth;
import com.challenge.challenge.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(ServerRoutes.AUTH)
    public ResponseEntity<?> auth(@RequestBody @Valid SimpleUserAuth userAuth, HttpServletRequest request) {
        return new ResponseEntity<>(authenticationService.authenticateUser(userAuth, request), HttpStatus.OK);
    }

    @PostMapping(ServerRoutes.TEST)
    public ResponseEntity<?> testAuth() {
        return new ResponseEntity<>(Arrays.asList("name", "data").toArray(),HttpStatus.OK);
    }
}
