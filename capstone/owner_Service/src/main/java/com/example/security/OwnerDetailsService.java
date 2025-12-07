package com.example.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Owner;
import com.example.repository.OwnerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OwnerDetailsService implements UserDetailsService {

//	owner repository injection
    private final OwnerRepository ownerRepository;

//    load owner by username (email)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//    	find owner by email
        Owner owner = ownerRepository.findByContactEmail(email);

        if (owner == null)
            throw new UsernameNotFoundException("Owner not found: " + email);

        return new User(
                owner.getContactEmail(),
                owner.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_OWNER"))
        );
    }
}
