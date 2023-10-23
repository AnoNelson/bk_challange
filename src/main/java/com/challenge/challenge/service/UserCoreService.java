package com.challenge.challenge.service;

import com.challenge.challenge.model.UserCore;
import com.challenge.challenge.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserCoreService {
    private final UserRepository userRepository;

    public UserCore findUserById(String id) {
        Optional<UserCore> user = userRepository.findById(id);
        if (user.isPresent())
            return user.get();
        else
            return null;
    }
}
