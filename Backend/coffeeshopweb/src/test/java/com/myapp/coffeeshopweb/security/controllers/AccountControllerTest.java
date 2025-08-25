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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.coffeeshopweb.security.models.Account;
import com.myapp.coffeeshopweb.security.services.AccountService;
import com.myapp.coffeeshopweb.security.services.RoleService;


@WebMvcTest( AccountController.class )
@WithMockUser
class AccountControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc ;
	
	@MockitoBean
	private AccountService accountService ;
	@MockitoBean
	private RoleService roleService ;
	@MockitoBean
	private PasswordEncoder passwordEncoder ;
	
	
	final String USERNAME_ACCOUNT_1 = "account1" ;
	final String USERNAME_ACCOUNT_2 = "account2" ;
	
	
	@BeforeEach
	void beforeEachTest () {
		
		Account account1 = new Account() ;
		account1.setId( "1" ) ;
		account1.setUsername( this.USERNAME_ACCOUNT_1 ) ;
		
		Account account2 = new Account() ;
		account2.setId( "2" ) ;
		account2.setUsername( this.USERNAME_ACCOUNT_2 ) ;
		
		List<Account> accountFindAllList = new ArrayList<Account>() ;
		accountFindAllList.add( account1 ) ;
		accountFindAllList.add( account2 ) ;
		
		List<Account> accountFindById1List = new ArrayList<Account>() ;
		accountFindById1List.add( account1 ) ;
		
		List<Account> accountFindById2List = new ArrayList<Account>() ;
		accountFindById2List.add( account2 ) ;
		
		
		when( this.accountService.findAll() ).thenReturn( accountFindAllList ) ;
		when( this.accountService.findById( account1.getId() ) ).thenReturn( accountFindById1List ) ;
		when( this.accountService.findById( account2.getId() ) ).thenReturn( accountFindById2List ) ;
		
		
	}
	
	
	@Test
	void findAllShouldReturnAllAccounts() throws Exception {
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/accounts" ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Account> responseAccountList = objectMapper.readValue(responseJson, new TypeReference<List<Account>>(){} ) ;

		
		verify( this.accountService, atLeastOnce() ).findAll() ;
		
		
		assertNotNull( responseAccountList, "responseAccountList is Null." ) ;
		
		assertTrue( responseAccountList.size() == 2, 
				    "responseAccountList.size() is " + 
				    responseAccountList.size() + 
		            " (Not 2)." ) ;
		
		assertTrue( responseAccountList.get( 0 ).getUsername().equals( this.USERNAME_ACCOUNT_1 ), 
				    "responseAccountList.get( 0 ) is " + 
				    responseAccountList.get( 0 ).getUsername() + 
				    " (not " + this.USERNAME_ACCOUNT_1 + ")." ) ;
		assertTrue( responseAccountList.get( 1 ).getUsername().equals( this.USERNAME_ACCOUNT_2 ), 
				    "responseAccountList.get( 1 ) is " + 
				    responseAccountList.get( 1 ).getUsername() + 
				    " (not " + this.USERNAME_ACCOUNT_2 + ")." ) ;
		
		
	}
	
	
	@Test
	void findByIdShouldReturnAllAccountsHavingProvidedId() throws Exception {
		
		final String PROVIDED_ACCOUNT_ID = "1" ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/accounts/" + PROVIDED_ACCOUNT_ID ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Account> responseAccountList = objectMapper.readValue(responseJson, new TypeReference<List<Account>>(){} ) ;

		
		verify( this.accountService, atLeastOnce() ).findById( PROVIDED_ACCOUNT_ID ) ;
		
		
		assertNotNull( responseAccountList, "responseAccountList is Null." ) ;
		
		assertTrue( responseAccountList.size() == 1, 
				    "responseAccountList.size() is " + 
				    responseAccountList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseAccountList.get( 0 ).getUsername().equals( this.USERNAME_ACCOUNT_1 ), 
				    "responseAccountList.get( 0 ) is " + 
				    responseAccountList.get( 0 ).getUsername() + 
				    " (not " + this.USERNAME_ACCOUNT_1 + ")." ) ;
		
		
	}

	

}
