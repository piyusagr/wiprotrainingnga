package com.playstore.service;

import java.time.LocalDateTime;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.playstore.dto.AppDownloadNotificationRequest;
import com.playstore.dto.AppUpdateNotificationRequest;
import com.playstore.entity.NotificationLog;
import com.playstore.repository.NotificationLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;
    private final NotificationLogRepository logRepo;

    public void sendDownloadNotification(AppDownloadNotificationRequest req) {
        String subject = "Your app was downloaded";
        String body = """
                Hi %s,
                
                Your app "%s" has just been downloaded by %s.
                
                Regards,
                Play Store Team
                """.formatted(req.ownerName(), req.appName(), req.userName());
        sendAndLog(req.ownerEmail(), subject, body, "DOWNLOAD");
    }

    public void sendUpdateNotification(AppUpdateNotificationRequest req) {
        String subject = "New update for " + req.appName();
        String body = """
                Hi,
                
                A new version %s of "%s" is now available.
                
                Open the app store to update.
                
                Regards,
                Play Store Team
                """.formatted(req.newVersion(), req.appName());
        req.userEmails().forEach(email ->
                sendAndLog(email, subject, body, "UPDATE"));
    }

    private void sendAndLog(String to, String subject, String body, String type) {
        boolean success = false;
        String error = null;
        try {
            var message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            success = true;
        } catch (Exception ex) {
            error = ex.getMessage();
        }
        NotificationLog log = new NotificationLog();
        log.setRecipient(to);
        log.setSubject(subject);
        log.setType(type);
        log.setSentAt(LocalDateTime.now());
        log.setSuccess(success);
        log.setErrorMessage(error);
        logRepo.save(log);
        if (!success) throw new RuntimeException("Failed to send email: " + error);
    }
}
