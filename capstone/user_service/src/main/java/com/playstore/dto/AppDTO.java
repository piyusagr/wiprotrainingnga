package com.playstore.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AppDTO {
    private Long id;
    private String name;
    private String description;
    private String version;
    private String releaseDate;
    private Double rating;
    private Double averageRating;
    private String genre;
    private boolean visibility;
    private Long ownerId;
    private Long categoryId;
    private Long downloadCount;
    private LocalDateTime createdAt;
    private String developer;
    private String developerEmail;
    private String iconUrl;
}
