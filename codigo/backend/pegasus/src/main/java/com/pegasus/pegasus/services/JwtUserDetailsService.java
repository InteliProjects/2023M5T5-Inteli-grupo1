package com.pegasus.pegasus.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pegasus.pegasus.DTOs.UserDTO;
import com.pegasus.pegasus.entities.User;
import com.pegasus.pegasus.entities.UserRole;
import com.pegasus.pegasus.repositories.UserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public Long getId(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return user.getId();
	}
	public User save(UserDTO user) {
		if(userRepository.findByUsernameCustom(user.getUsername()) != null){
			throw new RuntimeException("Username already exists");
		}
		if(userRepository.findByEmailCustom(user.getEmail()) != null){
			throw new RuntimeException("Email already exists");
		}
		User newUser = new User();
		newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setRole(UserRole.USER);
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));

		return userRepository.save(newUser);
	}
}