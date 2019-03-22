package com.invillia.acme.services;

import com.invillia.acme.models.User;

public interface UserService extends CrudService<User>{

	User findByUsername(String username);
	
}
