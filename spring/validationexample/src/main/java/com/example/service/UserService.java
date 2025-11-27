package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.UserRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.model.User;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepo;
	
	public User saveUSer(User u)
	{
		return userRepo.save(u);
	}
	
	public List<User> getAllUsers()
	{
		return userRepo.findAll();
	}
	
	public User getUserById(Long id)
	{
		return userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User Not Found with this id : " + id));
	}
	
	public User updateUserById(Long id, User user)
	{
		userRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Cannot update. User Not Found with id : " + id));
		
		return userRepo.save(user);
	}
	
	public User partialUpdateUserById(Long id , User user)
	{
		userRepo.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Cannot update. User Not Found with id : " + id));
		
		return userRepo.save(user);
	}
	
	public void deleteUserById(Long id) 
	{
		User user = userRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Cannot delete. User Not Found with id : " + id));
		
	    userRepo.delete(user);
	}

}