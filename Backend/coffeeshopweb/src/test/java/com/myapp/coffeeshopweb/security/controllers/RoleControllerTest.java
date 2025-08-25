package com.myapp.coffeeshopweb.security.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.coffeeshopweb.security.models.Role;
import com.myapp.coffeeshopweb.security.services.RoleService;



@WebMvcTest( RoleController.class )
@WithMockUser
class RoleControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc ;
	
	@MockitoBean
	private RoleService roleService ;
	
	
	final String ROLE_GUEST = "ROLE_GUEST" ;
	final String ROLE_HELPER = "ROLE_HELPER" ;
	final String ROLE_USER = "ROLE_USER" ;
	final String ROLE_ADMIN = "ROLE_ADMIN" ;
	
	
	
	@BeforeEach
	void beforeEachTest () {
		
		Role roleGuest = new Role() ;
		roleGuest.setId( "1" ) ;
		roleGuest.setName( this.ROLE_GUEST ) ;
		
		Role roleHelper = new Role() ;
		roleHelper.setId( "2" ) ;
		roleHelper.setName( this.ROLE_HELPER ) ;
		
		Role roleUser = new Role() ;
		roleUser.setId( "3" ) ;
		roleUser.setName( this.ROLE_USER ) ;
		
		Role roleAdmin = new Role() ;
		roleAdmin.setId( "4" ) ;
		roleAdmin.setName( this.ROLE_ADMIN ) ;
		
		List<Role> roleFindAllList = new ArrayList<Role>() ;
		roleFindAllList.add( roleGuest ) ;
		roleFindAllList.add( roleHelper ) ;
		roleFindAllList.add( roleUser ) ;
		roleFindAllList.add( roleAdmin ) ;
		
		List<Role> roleFindById1List = new ArrayList<Role>() ;
		roleFindById1List.add( roleGuest ) ;
		
		List<Role> roleFindById2List = new ArrayList<Role>() ;
		roleFindById2List.add( roleHelper ) ;
		
		List<Role> roleFindById3List = new ArrayList<Role>() ;
		roleFindById3List.add( roleUser ) ;
		
		List<Role> roleFindById4List = new ArrayList<Role>() ;
		roleFindById4List.add( roleAdmin ) ;
		
		
		when( this.roleService.findAll() ).thenReturn( roleFindAllList ) ;
		when( this.roleService.findById( roleGuest.getId() ) ).thenReturn( roleFindById1List ) ;
		when( this.roleService.findById( roleHelper.getId() ) ).thenReturn( roleFindById2List ) ;		
		when( this.roleService.findById( roleUser.getId() ) ).thenReturn( roleFindById3List ) ;
		when( this.roleService.findById( roleAdmin.getId() ) ).thenReturn( roleFindById4List ) ;
		
		when( this.roleService.findByName( roleGuest.getName() ) ).thenReturn( roleFindById1List ) ;
		when( this.roleService.findByName( roleHelper.getName() ) ).thenReturn( roleFindById2List ) ;		
		when( this.roleService.findByName( roleUser.getName() ) ).thenReturn( roleFindById3List ) ;
		when( this.roleService.findByName( roleAdmin.getName() ) ).thenReturn( roleFindById4List ) ;
		
		
		
	}

	
	@Test
	void findAllShouldReturnAllRoles() throws Exception {
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/roles" ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Role> responseRoleList = objectMapper.readValue(responseJson, new TypeReference<List<Role>>(){} ) ;

		
		verify( this.roleService, atLeastOnce() ).findAll() ;
		
		
		assertNotNull( responseRoleList, "responseRoleList is Null." ) ;
		
		assertTrue( responseRoleList.size() == 4, 
				    "responseRoleList.size() is " + 
				    responseRoleList.size() + 
		            " (Not 4)." ) ;
		
		assertTrue( responseRoleList.get( 0 ).getName().equals( this.ROLE_GUEST ), 
				    "responseRoleList.get( 0 ) is " + 
				    responseRoleList.get( 0 ).getName() + 
				    " (not " + this.ROLE_GUEST + ")." ) ;
		assertTrue( responseRoleList.get( 1 ).getName().equals( this.ROLE_HELPER ), 
				    "responseRoleList.get( 1 ) is " + 
				    responseRoleList.get( 1 ).getName() + 
				    " (not " + this.ROLE_HELPER + ")." ) ;
		assertTrue( responseRoleList.get( 2 ).getName().equals( this.ROLE_USER ), 
			    "responseRoleList.get( 2 ) is " + 
			    responseRoleList.get( 2 ).getName() + 
			    " (not " + this.ROLE_USER + ")." ) ;
		assertTrue( responseRoleList.get( 3 ).getName().equals( this.ROLE_ADMIN ), 
			    "responseRoleList.get( 3 ) is " + 
			    responseRoleList.get( 3 ).getName() + 
			    " (not " + this.ROLE_ADMIN + ")." ) ;

		
		
	}
	
	
	@Test
	void findByIdShouldReturnAllRolesHavingProvidedId() throws Exception {
		
		final String PROVIDED_ROLE_ID = "3" ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/roles/" + PROVIDED_ROLE_ID ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Role> responseRoleList = objectMapper.readValue(responseJson, new TypeReference<List<Role>>(){} ) ;

		
		verify( this.roleService, atLeastOnce() ).findById( PROVIDED_ROLE_ID ) ;
		
		
		assertNotNull( responseRoleList, "responseRoleList is Null." ) ;
		
		assertTrue( responseRoleList.size() == 1, 
				    "responseRoleList.size() is " + 
				    responseRoleList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseRoleList.get( 0 ).getName().equals( this.ROLE_USER ), 
				    "responseRoleList.get( 0 ) is " + 
				    responseRoleList.get( 0 ).getName() + 
				    " (not " + this.ROLE_USER + ")." ) ;
		
		
	}
	
	
	@Test
	void findByNameShouldReturnAllRolesHavingProvidedName() throws Exception {
		
		final String PROVIDED_ROLE_NAME = this.ROLE_ADMIN ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/roles/name/" + PROVIDED_ROLE_NAME ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Role> responseRoleList = objectMapper.readValue(responseJson, new TypeReference<List<Role>>(){} ) ;

		
		verify( this.roleService, atLeastOnce() ).findByName( PROVIDED_ROLE_NAME ) ;
		
		
		assertNotNull( responseRoleList, "responseRoleList is Null." ) ;
		
		assertTrue( responseRoleList.size() == 1, 
				    "responseRoleList.size() is " + 
				    responseRoleList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseRoleList.get( 0 ).getName().equals( this.ROLE_ADMIN ), 
				    "responseRoleList.get( 0 ) is " + 
				    responseRoleList.get( 0 ).getName() + 
				    " (not " + this.ROLE_ADMIN + ")." ) ;
		
		
	}

	

}
