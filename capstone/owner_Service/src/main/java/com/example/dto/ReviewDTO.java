package com.example.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private Long appId;
    private Long userId;
    private String username;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
