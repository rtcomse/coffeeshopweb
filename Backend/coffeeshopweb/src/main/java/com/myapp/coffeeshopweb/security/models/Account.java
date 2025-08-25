package com.myapp.coffeeshopweb.security.models;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.ObjectMapper;


@Document( "account" )
public class Account {
	
	@Id
	private String id ;
	
	@Field( "username" )
	private String username ;
	
	@Field( "password" )
	private String password ;
	
	@Field( "is_account_non_expired" )
	private boolean isAccountNonExpired ;
	
	@Field( "is_account_non_locked" )
	private boolean isAccountNonLocked ;
	
	@Field( "is_credentials_non_expired" )
	private boolean isCredentialsNonExpired ;
	
	@Field( "is_enabled" )
	private boolean isEnabled ;
	
	
	@Field( "roles" )
	private List<Role> roles ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( Account.class ) ;
	
	
	// Getters and Setters:
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}


	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}


	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}


	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}


	public boolean isEnabled() {
		return isEnabled;
	}


	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	// Method: toString():
	
	public String toString () {
		
		String retString = "" ;
		
		try {
			
			ObjectMapper objectMapper = new ObjectMapper() ;
			
			String stringJson = objectMapper.writeValueAsString( this ) ;
			
			
			retString = stringJson ;
			
		} 
		catch ( Exception e ) { 
			
			LOGGER.error( "Exception: {}", e ) ;
			
		}
		
		
		
		return retString ;
		
	}	

}
