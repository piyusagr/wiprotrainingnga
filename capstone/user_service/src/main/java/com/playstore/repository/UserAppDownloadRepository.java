package com.playstore.repository;

import com.playstore.entity.UserAppDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAppDownloadRepository extends JpaRepository<UserAppDownload, Long> {
    
    // Check if a user has already downloaded an app
    Optional<UserAppDownload> findByUserIdAndAppId(Long userId, Long appId);
    
    // Count apps downloaded by a user
    long countByUserId(Long userId);
    
    // send list of distinct user emails who downloaded a particular app
     @Query("SELECT DISTINCT u.email FROM User u WHERE u.id IN (SELECT uad.userId FROM UserAppDownload uad WHERE uad.appId = :appId)")
     List<String> findDistinctByAppId(Long appId);
}
