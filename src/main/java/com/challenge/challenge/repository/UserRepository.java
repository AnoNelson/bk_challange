package com.challenge.challenge.repository;

import com.challenge.challenge.model.UserCore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserCore, String> {

    UserCore findByUsername(String userName);

}
