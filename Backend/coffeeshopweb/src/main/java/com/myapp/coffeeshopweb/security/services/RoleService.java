package com.myapp.coffeeshopweb.security.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.coffeeshopweb.security.models.Role;
import com.myapp.coffeeshopweb.security.repositories.RoleRepository;


@Service
public class RoleService {
	
	
	@Autowired
	private RoleRepository roleRepository ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( RoleService.class ) ;
	
	
	public List<Role> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<Role> retRoleList = new ArrayList<Role>() ;
		
		
		retRoleList = this.roleRepository.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retRoleList ;
		
	}
	
	
	public List<Role> findById ( String id ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "id: {}", id ) ;
		
		List<Role> retRoleList = new ArrayList<Role>() ;
		
		List<String> idList = new ArrayList<String>() ;
		idList.add( id ) ;
		
		
		retRoleList = this.roleRepository.findAllById( idList ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retRoleList ;
		
	}
	
	
	public List<Role> findByName ( String name ) {

		LOGGER.debug( "ENTER: findByName ()" ) ;
		LOGGER.debug( "name: {}", name ) ;

		List<Role> retRoleList = new ArrayList<Role>() ;


		retRoleList = this.roleRepository.findByName( name ) ;


		LOGGER.debug( "EXIT: findByName ()" ) ;

		return retRoleList ;

	}

}
