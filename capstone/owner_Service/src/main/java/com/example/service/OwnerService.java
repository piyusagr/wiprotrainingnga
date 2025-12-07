package com.example.service;



import com.example.entity.Owner;

public interface OwnerService {
    Owner register(Owner owner);  // Register a new owner
    Owner login(String email, String password); // Owner login
    void logout(); // Owner logout
	void deleteOwner(Long id); // Delete owner by ID
	Owner updatePassword(Long id, String newPassword); // Update owner's password
	Owner updateOwner(Long id, Owner updatedOwner); // Update owner details
	Owner getOwnerById(Long id); // Get owner by ID
	Owner findByEmails(String email); // Find owner by email
}
