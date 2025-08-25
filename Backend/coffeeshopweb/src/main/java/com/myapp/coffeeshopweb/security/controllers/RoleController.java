package com.myapp.coffeeshopweb.security.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.coffeeshopweb.security.models.Role;
import com.myapp.coffeeshopweb.security.services.RoleService;


@RestController
public class RoleController {
	
	
	@Autowired
	private RoleService roleService ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( RoleController.class ) ;
	
	
	@GetMapping( "/roles" )
	public List<Role> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<Role> retRoleList = new ArrayList<Role>() ;
		
		
		retRoleList = this.roleService.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retRoleList ;
		
	}
	
	
	@GetMapping( "/roles/{roleId}" )
	public List<Role> findById ( @PathVariable String roleId ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "roleId: {}", roleId ) ;
		
		List<Role> retRoleList = new ArrayList<Role>() ;
		
		
		retRoleList = this.roleService.findById( roleId ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retRoleList ;
		
	}
	
	
	@GetMapping( "/roles/name/{roleName}" )
	public List<Role> findByName ( @PathVariable String roleName ) {
		
		LOGGER.debug( "ENTER: findByName ()" ) ;
		LOGGER.debug( "roleName: {}", roleName ) ;
		
		List<Role> retRoleList = new ArrayList<Role>() ;
		
		
		retRoleList = this.roleService.findByName( roleName ) ;
		
		
		LOGGER.debug( "EXIT: findByName ()" ) ;
		
		return retRoleList ;
		
	}
	
	

}
