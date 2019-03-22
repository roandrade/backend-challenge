package com.invillia.acme.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.invillia.acme.models.User;
import com.invillia.acme.security.UserDetailsImpl;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

	@Override
	public UserDetails convert(User user) {
		UserDetailsImpl userDetails = new UserDetailsImpl();
		
		if(user != null){
			userDetails.setUsername(user.getUsername());
			userDetails.setPassword(user.getPassword());
			userDetails.setEnabled(user.getActive());
		}
		
		return userDetails;
	}

}
