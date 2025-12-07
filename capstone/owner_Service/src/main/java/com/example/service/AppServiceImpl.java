package com.example.service;

import com.example.client.NotificationServiceClient;
import com.example.client.UserServiceClient;
import com.example.dto.AppUpdateNotificationRequest;
import com.example.entity.App;
import com.example.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppServiceImpl implements AppService {

    private final AppRepository appRepository;
    private final NotificationServiceClient notificationServiceClient;
    private final UserServiceClient userServiceClient;
    
//    create application 
    @Override
    public App createApp(App app) {
        return appRepository.save(app);
    }

//    update application
    @Override
    @Transactional(timeout = 30)
    public App updateApp(Long id, App appDetails) {
        App app = appRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("App not found with id: " + id));

        app.setName(appDetails.getName());
        app.setDescription(appDetails.getDescription());
        app.setReleaseDate(appDetails.getReleaseDate());
        app.setVersion(appDetails.getVersion());
        app.setGenre(appDetails.getGenre());
        app.setCategory(appDetails.getCategory());
        app.setVisibility(appDetails.isVisibility());

        
        App saved=appRepository.save(app);
        List<String> userEmails = userServiceClient.getDownloadersEmails(id);
        if (!userEmails.isEmpty()) {
            // build DTO
            AppUpdateNotificationRequest request =
                    new AppUpdateNotificationRequest(
                            userEmails,
                            saved.getName(),
                            saved.getVersion()
                    );

            // call notification microservice
            notificationServiceClient.sendUpdateNotification(request);
        }
        return saved;
    }

//    get application by id
    @Override
    public App getAppById(Long id) {
        return appRepository.findAppById(id);
    }

//    get all applications
    @Override
    public List<App> getAllApps() {
        return appRepository.findAll();
    }

//    delete particular application
    @Override
    @Transactional
    public void deleteApp(Long id) {
        appRepository.deleteById(id);
    }
    
//    find applications with visibility true
    @Override
    public List<App> getAppByTrueVisible() {
        return appRepository.findByVisibilityTrue();
    }
    
//    search apps by name and/or category with visibility true
    @Override
    public List<App> searchAppsandCategory(String keyword, Long categoryId) {
		return appRepository.findByNameAndCategory(keyword, categoryId);
	}
    
//    search apps by name with visibility true
    @Override
    public List<App> searchApps(String keyword) {
    	return appRepository.findByNameContainingIgnoreCase(keyword);
    }
    
//    search apps by category with visibility true
    @Override
    public List<App> searchByCategory(Long categoryId) {
		return appRepository.findByCategoryAndVisibility(categoryId);
	}
    
//    get app by id with visibility true
	@Override
	public App getByIdAndVisibilityTrue(Long id) {
		return appRepository.findByIdAndVisibilityTrue(id);
	}
	
	//    increase download count of an app
	@Override
	public Long incrementDownloadCounts(Long id) {
		return appRepository.incrementDownloadCount(id);
	}
	
	//   find apps by owner
	@Override
	public List<App> getAppsByOwnerId(Long ownerId) {
		return appRepository.findAllByOwnerId(ownerId);
	}

	
}


