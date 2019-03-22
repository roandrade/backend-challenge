package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.invillia.acme.models.User;
import com.invillia.acme.repositories.UserRepository;
import com.invillia.acme.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public User findByUsername(String username) {
		User User = userRepository.findByUsername(username);
		if(User == null){
			throw new IllegalArgumentException("User not found!");
		}
		return User;
	}

}
