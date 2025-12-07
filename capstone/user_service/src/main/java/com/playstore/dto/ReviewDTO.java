package com.playstore.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private Long appId;
    private Long userId;
    private String username;  // Display name
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}

