package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.dto.AppUpdateNotificationRequest;

@FeignClient(name = "NotificationService", url = "http://localhost:8083")
public interface NotificationServiceClient {


	@PostMapping("/notifications/app-update")
	ResponseEntity<Void> sendUpdateNotification(@RequestBody AppUpdateNotificationRequest request);
	
}
