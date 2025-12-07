package com.playstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playstore.entity.NotificationLog;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

}
