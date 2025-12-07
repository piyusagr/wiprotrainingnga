package com.playstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_app_downloads", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "app_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAppDownload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "app_id", nullable = false)
    private Long appId;
    
    @Column(name = "downloaded_at")
    private LocalDateTime downloadedAt = LocalDateTime.now();
}
