package com.playstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.playstore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	  Find user by username
    Optional<User> findByUsername(String username);

    //    Find user by email
    boolean existsByUsername(String username);
    
//    Find user by email
    boolean existsByEmail(String email);
    
     
//    delete by username
	void deleteByUsername(String username);
}

