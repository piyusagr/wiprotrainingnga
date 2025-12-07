package com.playstore.dto;
import java.util.List;

public record AppUpdateNotificationRequest(
        List<String> userEmails,
        String appName,
        String newVersion
) {}