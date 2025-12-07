package com.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.ReviewDTO;

@FeignClient(name = "user-service-client", url = "http://localhost:8081")
public interface UserServiceClient {

	// getting reviews by app id    
    @GetMapping("/api/reviews/app/{appId}")
    List<ReviewDTO> getReviewsByApp(@PathVariable("appId") Long appId);
    
    //geting list of emails who downloaded particular app
    @GetMapping("/app/{id}/downloaders")
    List<String> getDownloadersEmails(@PathVariable("id") Long appId);
} 
