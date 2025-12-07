
package com.playstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.playstore.dto.UserDTO;
import com.playstore.entity.User;
import com.playstore.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired private UserRepository repository;
    @Autowired private PasswordEncoder passwordEncoder;
    
//    Register a new user
    public User register(UserDTO dto) {
        if (repository.existsByUsername(dto.getUsername()) || repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole() );
        return repository.save(user);
    }
    
    
//    User login
    public Optional<User> login(String username, String password) {
        return repository.findByUsername(username)
            .filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }
    
//    Get all users
    public List<User> getAllUsers() {
        return repository.findAll();
    }
    
//    Get user by username
    public Optional<User> findByUsernames(String username){
        return repository.findByUsername(username);
    }
    
//    Update user
    public User updateUser(User user) {
    	if (!repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User doesnot exists");
        }
    	return repository.save(user);
    }
    
    
//    Delete user by username
    public void deleteByUsername(String username) {
    	if (!repository.existsByUsername(username)) {
            throw new RuntimeException("User doesnot exists");
        }
    	repository.deleteByUsername(username);
    }
}
