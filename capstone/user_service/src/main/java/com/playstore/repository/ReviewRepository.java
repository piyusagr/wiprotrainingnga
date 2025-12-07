package com.playstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playstore.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Find reviews by appId
	List<Review> findByAppIdOrderByCreatedAtDesc(Long appId);

    // Find reviews by userId
    List<Review> findByUserIdOrderByCreatedAtDesc(Long userId);

    // Find reviews by appId and userId
    Optional<Review> findFirstByAppIdAndUserIdOrderByCreatedAtDesc(Long appId, Long userId);
    
}
