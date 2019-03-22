package com.invillia.acme.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.invillia.acme.converters.UserToUserDetails;
import com.invillia.acme.services.UserService;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDetails converter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return converter.convert(userService.findByUsername(username));
	}

}
