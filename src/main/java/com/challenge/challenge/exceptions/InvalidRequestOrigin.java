package com.challenge.challenge.exceptions;

public class InvalidRequestOrigin extends RuntimeException {
    public InvalidRequestOrigin(String message) {
        super(message);
    }
}
