package com.playstore.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.playstore.dto.AppDownloadNotificationRequest;

@FeignClient(name = "NotificationService", url = "http://localhost:8083")
public interface NotificationServiceClient {
	
	@PostMapping("/notifications/app-download")
    void sendDownloadNotification(@RequestBody AppDownloadNotificationRequest request);
	
}
