package com.myapp.coffeeshopweb.security.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.myapp.coffeeshopweb.security.models.Account;
import com.myapp.coffeeshopweb.security.models.Role;
import com.myapp.coffeeshopweb.security.repositories.AccountRepository;
import com.myapp.coffeeshopweb.services.CoffeeOrderService;


@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository ;
	
	@Autowired
	private RoleService roleService ;
	@Autowired
	private CoffeeOrderService coffeeOrderService ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( AccountService.class ) ;
	
	
	
	public List<Account> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<Account> retAccountList = new ArrayList<Account>() ;
		
		
		retAccountList = this.accountRepository.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retAccountList ;
		
	}
	
	
	public List<Account> findById ( String id ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "id: {}", id ) ;
		
		List<Account> retAccountList = new ArrayList<Account>() ;
		
		List<String> idList = new ArrayList<String>() ;
		idList.add( id ) ;
		
		
		retAccountList = this.accountRepository.findAllById( idList ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retAccountList ;
		
	}
	
	
	public List<Account> findByUsername ( String username ) {
		
		LOGGER.debug( "ENTER: findByUsername ()" ) ;
		LOGGER.debug( "username: {}", username ) ;
		
		List<Account> retAccountList = new ArrayList<Account>() ;
		
		
		retAccountList = this.accountRepository.findByUsername( username ) ;
		
		
		LOGGER.debug( "EXIT: findByUsername ()" ) ;
		
		return retAccountList ;
		
	}
	
	
	public List<Account> findByAuthenticated () {
		
		LOGGER.debug( "ENTER: findByAuthenticated ()" ) ;
		
		List<Account> retAuthenticatedAccountList = new ArrayList<Account>() ;

		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication() ;
		
		if ( ( authentication != null ) && 
			 ( authentication.isAuthenticated() ) && 
			 ( authentication.getPrincipal() instanceof UserDetails ) ) {
			
			Object principal = authentication.getPrincipal() ;
			UserDetails userDetails = (UserDetails) principal ;
			
			retAuthenticatedAccountList = this.findByUsername( userDetails.getUsername() ) ;
			
		}
		
		
		LOGGER.debug( "EXIT: findByAuthenticated ()" ) ;
		
		return retAuthenticatedAccountList ;

		
	}	
	
		
	public List<Account> findByGuest () {
		
		LOGGER.debug( "ENTER: findByGuest ()" ) ;
		
		List<Account> retGuestAccountList = new ArrayList<Account>() ;


		final String GUEST_ACCOUNT_USERNAME = "Guest" ;
		retGuestAccountList = this.findByUsername( GUEST_ACCOUNT_USERNAME ) ;
		
		
		List<Role> guestRoleList = this.roleService.findAll() ;
		
		for ( int i = 0 ; i < guestRoleList.size() ; i++ ) {
			
			if ( !( guestRoleList.get( i ).getName().equals( "ROLE_GUEST" ) ) ) {
				
				guestRoleList.remove( i ) ;
				i-- ;
				
			}
			
		}
		
		if ( guestRoleList.size() > 0 ) {
			
			Role currGuestRole = guestRoleList.getFirst() ;
			
			List<Account> currAccountList = this.findAll() ;
			
			Account currGuestAccount = null ;
			
			boolean foundGuestAccount = false ;
			
			for ( int i = 0 ; i < currAccountList.size() ; i++ ) {
				
				List<Role> currRoleList = currAccountList.get(i).getRoles() ;
				
				for ( int j = 0 ; j < currRoleList.size() ; j++ ) {
					
					if ( currRoleList.get(j).getId().equals( currGuestRole.getId() ) ) {
						
						foundGuestAccount = true ;
						
						currGuestAccount = currAccountList.get(i) ;
						
						break ;
						
					}
					
				}
				
				if ( foundGuestAccount ) {
					
					break ;
					
				}
				
			}
			
			if ( foundGuestAccount ) {
				
				retGuestAccountList = new ArrayList<Account>() ;
				retGuestAccountList.add( currGuestAccount ) ;
				
			}
			
		}
		
				
		
		
		LOGGER.debug( "EXIT: findByGuest ()" ) ;
		
		return retGuestAccountList ;

		
	}	
	
	
	public List<Account> createAccount ( String inputUsername, 
			                             String inputPassword, 
			                             List<Role> inputRoleList ) {
		
		LOGGER.debug( "ENTER: createAccount ()" ) ;
		LOGGER.debug( "inputUsername: {}", inputUsername ) ;
		LOGGER.debug( "inputPassword: {}", inputPassword ) ;
		LOGGER.debug( "inputRoleList: {}", inputRoleList ) ;
		
		List<Account> createdAccountList = new ArrayList<Account>() ;
		
		
		if ( inputUsername == null || 
			 inputUsername.length() == 0 || 
			 this.findByUsername( inputUsername ).size() != 0 ) {
			
			LOGGER.error( "Problem with inputUsername: {}", inputUsername ) ;
			LOGGER.debug( "EXIT: createAccount ()" ) ;
			
			return createdAccountList ;
			
		}		
		if ( inputPassword == null || 
			 inputPassword.length() == 0 ) {
			
			LOGGER.error( "Problem with inputPassword: {}", inputPassword ) ;
			LOGGER.debug( "EXIT: createAccount ()" ) ;
				
			return createdAccountList ;
				
		}
		
		Account currAccount = new Account() ;
		
		currAccount.setId( null ) ;
		currAccount.setUsername( inputUsername ) ;
		currAccount.setPassword( inputPassword ) ;
		currAccount.setAccountNonExpired( true ) ;
		currAccount.setAccountNonLocked( true ) ;
		currAccount.setCredentialsNonExpired( true ) ;
		currAccount.setEnabled( true ) ;
		currAccount.setRoles( inputRoleList ) ;
		
		
		currAccount = this.accountRepository.save( currAccount ) ;
		
		createdAccountList.add( currAccount ) ;
		
		
		LOGGER.debug( "EXIT: createAccount ()" ) ;
		
		return createdAccountList ;
		
	}
		
	public Boolean deleteAccount ( String inputAccountId ) {
		
		LOGGER.debug( "ENTER: deleteAccount ()" ) ;
		LOGGER.debug( "inputAccountId: {}", inputAccountId ) ;
		
		Boolean retSuccessful = Boolean.valueOf( false ) ;
		
		List<Account> matchedAccountList = this.findById( inputAccountId ) ;
		
		if ( matchedAccountList.size() == 0 ) {
			
			LOGGER.error( "Could not find account with id: {}", inputAccountId ) ;
			LOGGER.debug( "EXIT: deleteAccount ()" ) ;
			
			return retSuccessful ;
			
		}
		
		Account matchedAccount = matchedAccountList.getFirst() ;
		
		matchedAccount.getRoles().clear() ;
		
		this.coffeeOrderService.deleteByOrderedByUsername( matchedAccount.getUsername() ) ;
		
		this.accountRepository.delete( matchedAccount ) ; 
		
		
		retSuccessful = Boolean.valueOf( true ) ;
		
		
		LOGGER.debug( "EXIT: deleteAccount ()" ) ;
		
		return retSuccessful ;

	}
	

}
