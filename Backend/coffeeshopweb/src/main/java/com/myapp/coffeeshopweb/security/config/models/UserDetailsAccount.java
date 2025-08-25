package com.myapp.coffeeshopweb.security.config.models;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.coffeeshopweb.security.models.Account;


public class UserDetailsAccount implements UserDetails {
	

	private String username ;	
	private String password ;	
	private boolean isAccountNonExpired ;	
	private boolean isAccountNonLocked ;	
	private boolean isCredentialsNonExpired ;	
	private boolean isEnabled ;
	
	private List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>() ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( UserDetailsAccount.class ) ;
	
	
	
	// Constructors:
	
	public UserDetailsAccount ( Account inputAccount ) {
		
		
        this.username = new String( inputAccount.getUsername() ) ;
        this.password = new String( inputAccount.getPassword() ) ;
        this.isAccountNonExpired = inputAccount.isAccountNonExpired() ;
        this.isAccountNonLocked = inputAccount.isAccountNonLocked() ;
        this.isCredentialsNonExpired = inputAccount.isCredentialsNonExpired() ;
        this.isEnabled = inputAccount.isEnabled() ;	
        
        
        for ( int i = 0 ; i < inputAccount.getRoles().size() ; i++ ) {
        	
        	this.authorities.add( new SimpleGrantedAuthority( inputAccount.getRoles().get( i ).getName() ) ) ;
        	
        }
        
        
        
		
	}
	
	public UserDetailsAccount () {

	}
	
	
	// Getters and Setters:

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
		
	public List<SimpleGrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
		this.authorities = authorities;
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
