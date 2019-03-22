package com.invillia.acme.services;

import com.invillia.acme.models.User;

public interface UserService{

	User findByUsername(String username);
	
}
