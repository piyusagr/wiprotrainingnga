package com.example.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Owner;
import com.example.repository.OwnerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // REGISTER OWNER
    @Override
    public Owner register(Owner owner) {
    	owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        return ownerRepository.save(owner);
    }

    // LOGIN OWNER
    @Override
    public Owner login(String email, String password) {
        Owner owner = ownerRepository.findByContactEmail(email);

        if (owner == null)            
        	throw new RuntimeException("Owner does not exist!");

        if (!passwordEncoder.matches(password, owner.getPassword()))
            throw new RuntimeException("Invalid password!");

        return owner;
    }

    // LOGOUT (SPRINT 2 → JWT)
    @Override
    public void logout() {
        // For now do nothing - HTML session logout only
    }

    // GET OWNER BY ID
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Owner not found!"));
    }

    

    // UPDATE OWNER DETAILS
    public Owner updateOwner(Long id, Owner updatedOwner) {
        Owner existing = getOwnerById(id);

        existing.setDisplayName(updatedOwner.getDisplayName());
        existing.setContactEmail(updatedOwner.getContactEmail());

        return ownerRepository.save(existing);
    }

    // UPDATE OWNER PASSWORD
    public Owner updatePassword(Long id, String newPassword) {
        Owner owner = getOwnerById(id);
    	owner.setPassword(passwordEncoder.encode(newPassword));
        return ownerRepository.save(owner);
    }

    // DELETE OWNER
    public void deleteOwner(Long id) {
        if (!ownerRepository.existsById(id)) {
            throw new RuntimeException("Owner not found!");
        }
        ownerRepository.deleteById(id);
    }
    
    //find owner by email
    @Override
    public Owner findByEmails(String email) {
		return ownerRepository.findByContactEmail(email);
	}
}
