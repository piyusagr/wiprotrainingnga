package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "owners")
@Data @NoArgsConstructor @AllArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This maps to user ID from user-service
    private Long userId;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false, unique = true)
    private String contactEmail;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;

    @PrePersist
    public void preCreate() {
        createdAt = LocalDateTime.now();
    }
}
