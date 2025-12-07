package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
//    find owner by contact email
	Owner findByContactEmail(String email);
    
}
