package com.example.service;

import com.example.entity.App;

import java.util.List;


public interface AppService {
    App createApp(App app); // Create a new app
    App updateApp(Long id, App app); // Update an existing app
    App getAppById(Long id); // Get app by ID
    List<App> getAllApps(); // Get all apps
    void deleteApp(Long id); // Delete app by ID
    List<App> getAppByTrueVisible(); // Get all apps with visibility true
    List<App> searchAppsandCategory(String keyword, Long categoryId); // Search apps by name and/or category with visibility true
    List<App> searchApps(String keyword); // Search apps by name with visibility true
    List<App> searchByCategory(Long categoryId); // Search apps by category with visibility true
    App getByIdAndVisibilityTrue(Long id); // Get app by ID with visibility true
    Long incrementDownloadCounts(Long id); // Increase download count of an app
    List<App> getAppsByOwnerId(Long ownerId); // Find apps by owner
}
