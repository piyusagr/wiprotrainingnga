package com.playstore.controller;

import com.playstore.dto.AppDownloadNotificationRequest;
import com.playstore.dto.AppUpdateNotificationRequest;
import com.playstore.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/app-download")
    public ResponseEntity<Void> appDownload(@RequestBody AppDownloadNotificationRequest req) {
        notificationService.sendDownloadNotification(req);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/app-update")
    public ResponseEntity<Void> appUpdate(@RequestBody AppUpdateNotificationRequest req) {
        notificationService.sendUpdateNotification(req);
        return ResponseEntity.accepted().build();
    }
}
