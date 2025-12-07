package com.playstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.playstore.repository.*;

import java.util.List;

@Service
@Transactional
public class UserAppDownloadService {

    @Autowired private UserAppDownloadRepository repository;
    
    public List<String> getUsersByAppId(Long appId) {
		return repository.findDistinctByAppId(appId);
	}
    
}
