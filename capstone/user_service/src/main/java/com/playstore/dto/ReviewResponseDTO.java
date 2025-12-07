package com.playstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private String status;
    private String message;
    private ReviewDTO review;

    public ReviewResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
        this.review = null;
    }
}
