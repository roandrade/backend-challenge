package com.invillia.acme.controllers;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.invillia.acme.models.User;
import com.invillia.acme.security.JWTConfigurer;
import com.invillia.acme.security.TokenProvider;


@RestController
@RequestMapping("/")
public class AuthController {
	
    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    
	@SuppressWarnings("rawtypes")
	@PostMapping("/authentications")
	public ResponseEntity authorize(@Valid @RequestBody User user, HttpServletResponse response) {
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		try{
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			String jwt = tokenProvider.createToken(authentication, false);
			response.addHeader(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
			return ResponseEntity.ok(new JWTToken(jwt, (UserDetails) authentication.getPrincipal()));
		}catch(AuthenticationException ae){
			log.trace("Authentication exception {}", ae);
			return new ResponseEntity<>(Collections.singletonMap("AuthenticationException", ae.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request){
		String jwt = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
		if(jwt != null){
			jwt = jwt.replace("Bearer ", "");
			tokenProvider.removeToken(jwt);
			return ResponseEntity.ok().build();
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
				
	}

	
	   /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;
        private UserDetails user;

        JWTToken(String idToken, UserDetails user) {
            this.idToken = idToken;
            this.user = user;            
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("user")
		UserDetails getUser() {
			return user;
		}

		void setUser(UserDetails user) {
			this.user = user;
		}
        
        
    }

}
