package com.example.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "apps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @NotNull(message = "Owner is required")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Owner owner;
    private String name;
    private String description;
    private LocalDate releaseDate = LocalDate.now();
    private String version;
    private String genre;
    private boolean visibility = true;
    private long downloadCount = 0;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
    
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonProperty("developer")
    public String getDeveloper() {
        return owner != null ? owner.getDisplayName() : null;
    }

    @JsonProperty("developerEmail")
    public String getDeveloperEmail() {
        return owner != null ? owner.getContactEmail() : null;
    }
}
