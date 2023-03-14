package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entities.UserEntity;
import com.app.repository.UserRepository;

import lombok.ToString;

@Service 
@Transactional
@Component
public class CustomUserDetailsService implements UserDetailsService {
	// dep : user repository : based upon spring data JPA
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("in load by user nm " + username);
		// invoke dao's method to load user details from db by username(ie. actaully an
		// email)
		UserEntity user = userRepo.
				findByUserName(username).
				orElseThrow(() -> new UsernameNotFoundException("Invalid username  "));
		System.out.println("lifted user dtls from db "+user);
		return new CustomUserDetails(user);
	}

}
