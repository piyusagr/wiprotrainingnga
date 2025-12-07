package com.playstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.playstore.dto.ReviewDTO;
import com.playstore.dto.ReviewResponseDTO;
import com.playstore.entity.User;
import com.playstore.security.JwtUtil;
import com.playstore.service.ReviewService;
import com.playstore.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public ReviewController(ReviewService reviewService, UserService userService, JwtUtil jwtUtil) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // CREATE - Submit a review
    @PostMapping
    public ResponseEntity<?> createReview(HttpServletRequest request, @RequestBody ReviewDTO reviewDTO) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            User user = userService.findByUsernames(username).orElseThrow();
            
            // Check if user already reviewed this app
            if (reviewService.hasUserReviewedApp(user.getId(), reviewDTO.getAppId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ReviewResponseDTO("error", "You have already reviewed this app"));
            }

            // Validate rating (1-5)
            if (reviewDTO.getRating() < 1 || reviewDTO.getRating() > 5) {
                return ResponseEntity.badRequest()
                    .body(new ReviewResponseDTO("error", "Rating must be between 1 and 5"));
            }

            ReviewDTO created = reviewService.createReview(username, reviewDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ReviewResponseDTO("success", "Review submitted successfully", created));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ReviewResponseDTO("error", e.getMessage()));
        }
    }

    // READ - Get all reviews for an app
    @GetMapping(value="/app/{appId}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getReviewsByApp(@PathVariable Long appId) {
        try {
            List<ReviewDTO> reviews = reviewService.getReviewsByAppId(appId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ReviewResponseDTO("error", e.getMessage()));
        }
    }

    // READ - Get all reviews by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReviewsByUser(@PathVariable Long userId) {
        try {
            List<ReviewDTO> reviews = reviewService.getReviewsByUserId(userId);
            return ResponseEntity.ok(reviews);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ReviewResponseDTO("error", e.getMessage()));
        }
    }

    // UPDATE - Update a review
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(HttpServletRequest request, 
                                         @PathVariable Long reviewId, 
                                         @RequestBody ReviewDTO reviewDTO) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            // Validate rating
            if (reviewDTO.getRating() < 1 || reviewDTO.getRating() > 5) {
                return ResponseEntity.badRequest()
                    .body(new ReviewResponseDTO("error", "Rating must be between 1 and 5"));
            }

            ReviewDTO updated = reviewService.updateReview(reviewId, reviewDTO, username);
            return ResponseEntity.ok(new ReviewResponseDTO("success", "Review updated successfully", updated));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Not authorized")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ReviewResponseDTO("error", "You can only edit your own reviews"));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ReviewResponseDTO("error", e.getMessage()));
        }
    }

    // DELETE - Delete a review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(HttpServletRequest request, @PathVariable Long reviewId) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            reviewService.deleteReview(reviewId, username);
            return ResponseEntity.ok(new ReviewResponseDTO("success", "Review deleted successfully"));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Not authorized")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ReviewResponseDTO("error", "You can only delete your own reviews"));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ReviewResponseDTO("error", e.getMessage()));
        }
    }

    // GET - Check if user already reviewed app
    @GetMapping("/check/{appId}")
    public ResponseEntity<?> checkReview(HttpServletRequest request, @PathVariable Long appId) {
        String username = getUsernameFromJwt(request);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            User user = userService.findByUsernames(username).orElseThrow();
            boolean hasReviewed = reviewService.hasUserReviewedApp(user.getId(), appId);
            return ResponseEntity.ok(new ReviewCheckDTO(appId, hasReviewed));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //  Extract username from JWT cookie
    private String getUsernameFromJwt(HttpServletRequest request) {
        jakarta.servlet.http.Cookie[] cookies = request.getCookies();  
        if (cookies != null) {
            for (jakarta.servlet.http.Cookie cookie : cookies) {   
                if ("jwt_token".equals(cookie.getName())) {
                    try {
                        String jwt = cookie.getValue();
                        String username = jwtUtil.extractUsername(jwt);
                        if (jwtUtil.isTokenValid(jwt, username)) {
                            return username;
                        }
                    } catch (Exception e) {
                        // Invalid JWT
                    }
                    break;
                }
            }
        }
        return null;
    }



    
    // Inner DTO for response
    static class ReviewCheckDTO {
        public Long appId;
        public boolean hasReviewed;

        public ReviewCheckDTO(Long appId, boolean hasReviewed) {
            this.appId = appId;
            this.hasReviewed = hasReviewed;
        }
    }
}
