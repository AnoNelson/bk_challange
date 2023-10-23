package com.challenge.challenge.repository;

import com.challenge.challenge.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {
     int countAllByStatus(String status);
     Like 

}
