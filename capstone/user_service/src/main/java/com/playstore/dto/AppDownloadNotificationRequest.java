package com.playstore.dto;

public record AppDownloadNotificationRequest(
        String ownerEmail,
        String ownerName,
        String appName,
        String userName
) {}
