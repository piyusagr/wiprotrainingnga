package com.playstore.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playstore.dto.ReviewDTO;
import com.playstore.entity.Review;
import com.playstore.entity.User;
import com.playstore.repository.ReviewRepository;
import com.playstore.repository.UserRepository;

@Service
@Transactional
public class ReviewService {
    
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository; 
    
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }
    
    // CREATE review
    public ReviewDTO createReview(String username, ReviewDTO reviewDTO) {
        // Validate user exists
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        // Check if already reviewed
        if (reviewRepository.findFirstByAppIdAndUserIdOrderByCreatedAtDesc(reviewDTO.getAppId(), user.getId()).isPresent()) {
            throw new RuntimeException("User already reviewed this app");
        }
        
        Review review = Review.builder()
            .appId(reviewDTO.getAppId())
            .user(user)
            .rating(reviewDTO.getRating())
            .comment(reviewDTO.getComment())
            .createdAt(LocalDateTime.now())
            .build();
            
        Review saved = reviewRepository.save(review);
        return mapToDTO(saved);
    }
    
    // READ reviews by appid
    public List<ReviewDTO> getReviewsByAppId(Long appId) {
        return reviewRepository.findByAppIdOrderByCreatedAtDesc(appId)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    //    READ reviews by userid
    public List<ReviewDTO> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserIdOrderByCreatedAtDesc(userId)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    //  UPDATE review
    public ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO, String username) {
        Review existing = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        
        User currentUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        // Check user entity matches
        if (!existing.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Not authorized to update this review");
        }
        
        existing.setRating(reviewDTO.getRating());
        existing.setComment(reviewDTO.getComment());
        existing.setCreatedAt(LocalDateTime.now());
        
        Review updated = reviewRepository.save(existing);
        return mapToDTO(updated);
    }
    
    // DELETE review
    public void deleteReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new RuntimeException("Review not found"));
        
        User currentUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (!review.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Not authorized to delete this review");
        }
        
        reviewRepository.delete(review);
    }
    
    //  Average rating
    public Double getAverageRating(Long appId) {
        List<Review> reviews = reviewRepository.findByAppIdOrderByCreatedAtDesc(appId);
        return reviews.stream()
            .mapToInt(Review::getRating)
            .average()
            .orElse(0.0);
    }
    
    //  Check if user reviewed app
    public boolean hasUserReviewedApp(Long userId, Long appId) {
        return reviewRepository.findFirstByAppIdAndUserIdOrderByCreatedAtDesc(appId, userId).isPresent();
    }
    
    // Complete DTO mapping
    private ReviewDTO mapToDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setAppId(review.getAppId());
        if (review.getUser() != null) {
            dto.setUserId(review.getUser().getId());
            dto.setUsername(review.getUser().getUsername());
        } else {
            dto.setUserId(null);
            dto.setUsername("Anonymous");
        }
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
