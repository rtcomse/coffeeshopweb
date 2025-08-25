package com.myapp.coffeeshopweb.controllers;

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
import com.myapp.coffeeshopweb.models.IngredientOption;
import com.myapp.coffeeshopweb.services.IngredientOptionService;


@WebMvcTest( IngredientOptionController.class )
@WithMockUser
class IngredientOptionControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc ;
	
	@MockitoBean
	private IngredientOptionService ingredientOptionService ;
	
	
	final String NAME_INGREDIENT_OPTION_1 = "ingredient_option_1_name" ;
	final String NAME_INGREDIENT_OPTION_2 = "ingredient_option_2_name" ;
	final String NAME_INGREDIENT_OPTION_3 = "ingredient_option_3_name" ;
	final String NAME_INGREDIENT_OPTION_4 = "ingredient_option_4_name" ;
	
	
	
	
	@BeforeEach
	void beforeEachTest () {
		
		IngredientOption ingredient_option_1  = new IngredientOption() ;
		ingredient_option_1.setId( "1" ) ;
		ingredient_option_1.setName( this.NAME_INGREDIENT_OPTION_1 ) ;
		
		IngredientOption ingredient_option_2  = new IngredientOption() ;
		ingredient_option_2.setId( "2" ) ;
		ingredient_option_2.setName( this.NAME_INGREDIENT_OPTION_2 ) ;
		
		IngredientOption ingredient_option_3  = new IngredientOption() ;
		ingredient_option_3.setId( "3" ) ;
		ingredient_option_3.setName( this.NAME_INGREDIENT_OPTION_3 ) ;
		
		IngredientOption ingredient_option_4  = new IngredientOption() ;
		ingredient_option_4.setId( "4" ) ;
		ingredient_option_4.setName( this.NAME_INGREDIENT_OPTION_4 ) ;
		
		
		List<IngredientOption> ingredientOptionFindAllList = new ArrayList<IngredientOption>() ;
		ingredientOptionFindAllList.add( ingredient_option_1 ) ;
		ingredientOptionFindAllList.add( ingredient_option_2 ) ;
		ingredientOptionFindAllList.add( ingredient_option_3 ) ;
		ingredientOptionFindAllList.add( ingredient_option_4 ) ;
		
		List<IngredientOption> ingredientOptionFindById1List = new ArrayList<IngredientOption>() ;
		ingredientOptionFindById1List.add( ingredient_option_1 ) ;
		
		List<IngredientOption> ingredientOptionFindById2List = new ArrayList<IngredientOption>() ;
		ingredientOptionFindById2List.add( ingredient_option_2 ) ;
		
		List<IngredientOption> ingredientOptionFindById3List = new ArrayList<IngredientOption>() ;
		ingredientOptionFindById3List.add( ingredient_option_3 ) ;
		
		List<IngredientOption> ingredientOptionFindById4List = new ArrayList<IngredientOption>() ;
		ingredientOptionFindById4List.add( ingredient_option_4 ) ;
		
		
		when( this.ingredientOptionService.findAll() ).thenReturn( ingredientOptionFindAllList ) ;
		when( this.ingredientOptionService.findById( ingredient_option_1.getId() ) ).thenReturn( ingredientOptionFindById1List ) ;
		when( this.ingredientOptionService.findById( ingredient_option_2.getId() ) ).thenReturn( ingredientOptionFindById2List ) ;		
		when( this.ingredientOptionService.findById( ingredient_option_3.getId() ) ).thenReturn( ingredientOptionFindById3List ) ;
		when( this.ingredientOptionService.findById( ingredient_option_4.getId() ) ).thenReturn( ingredientOptionFindById4List ) ;
		
		when( this.ingredientOptionService.findByName( ingredient_option_1.getName() ) ).thenReturn( ingredientOptionFindById1List ) ;
		when( this.ingredientOptionService.findByName( ingredient_option_2.getName() ) ).thenReturn( ingredientOptionFindById2List ) ;		
		when( this.ingredientOptionService.findByName( ingredient_option_3.getName() ) ).thenReturn( ingredientOptionFindById3List ) ;
		when( this.ingredientOptionService.findByName( ingredient_option_4.getName() ) ).thenReturn( ingredientOptionFindById4List ) ;
		
		
		
		
	}

	
	@Test
	void findAllShouldReturnAllIngredientOptions() throws Exception {
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/ingredient_options" ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<IngredientOption> responseIngredientOptionList = objectMapper.readValue(responseJson, new TypeReference<List<IngredientOption>>(){} ) ;

		
		verify( this.ingredientOptionService, atLeastOnce() ).findAll() ;
		
		
		assertNotNull( responseIngredientOptionList, "responseIngredientOptionList is Null." ) ;
		
		assertTrue( responseIngredientOptionList.size() == 4, 
				    "responseIngredientOptionList.size() is " + 
				    responseIngredientOptionList.size() + 
		            " (Not 4)." ) ;
		
		assertTrue( responseIngredientOptionList.get( 0 ).getName().equals( this.NAME_INGREDIENT_OPTION_1 ), 
				    "responseIngredientOptionList.get( 0 ) is " + 
				    responseIngredientOptionList.get( 0 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_OPTION_1 + ")." ) ;
		assertTrue( responseIngredientOptionList.get( 1 ).getName().equals( this.NAME_INGREDIENT_OPTION_2 ), 
				    "responseIngredientOptionList.get( 1 ) is " + 
				    responseIngredientOptionList.get( 1 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_OPTION_2 + ")." ) ;
		assertTrue( responseIngredientOptionList.get( 2 ).getName().equals( this.NAME_INGREDIENT_OPTION_3 ), 
			    "responseIngredientOptionList.get( 2 ) is " + 
			    responseIngredientOptionList.get( 2 ).getName() + 
			    " (not " + this.NAME_INGREDIENT_OPTION_3 + ")." ) ;
		assertTrue( responseIngredientOptionList.get( 3 ).getName().equals( this.NAME_INGREDIENT_OPTION_4 ), 
			    "responseIngredientOptionList.get( 3 ) is " + 
			    responseIngredientOptionList.get( 3 ).getName() + 
			    " (not " + this.NAME_INGREDIENT_OPTION_4 + ")." ) ;

		
		
	}
	
	
	@Test
	void findByIdShouldReturnAllIngredientOptionsHavingProvidedId() throws Exception {
		
		final String PROVIDED_INGREDIENT_OPTION_ID = "3" ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/ingredient_options/" + PROVIDED_INGREDIENT_OPTION_ID ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<IngredientOption> responseIngredientOptionList = objectMapper.readValue(responseJson, new TypeReference<List<IngredientOption>>(){} ) ;

		
		verify( this.ingredientOptionService, atLeastOnce() ).findById( PROVIDED_INGREDIENT_OPTION_ID ) ;
		
		
		assertNotNull( responseIngredientOptionList, "responseIngredientOptionList is Null." ) ;
		
		assertTrue( responseIngredientOptionList.size() == 1, 
				    "responseIngredientOptionList.size() is " + 
				    responseIngredientOptionList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseIngredientOptionList.get( 0 ).getName().equals( this.NAME_INGREDIENT_OPTION_3 ), 
				    "responseIngredientOptionList.get( 0 ) is " + 
				    responseIngredientOptionList.get( 0 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_OPTION_3 + ")." ) ;
		
		
	}
	
	
	@Test
	void findByNameShouldReturnAllIngredientOptionsHavingProvidedName() throws Exception {
		
		final String PROVIDED_INGREDIENT_OPTION_NAME = this.NAME_INGREDIENT_OPTION_4 ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/ingredient_options/name/" + PROVIDED_INGREDIENT_OPTION_NAME ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<IngredientOption> responseIngredientOptionList = objectMapper.readValue(responseJson, new TypeReference<List<IngredientOption>>(){} ) ;

		
		verify( this.ingredientOptionService, atLeastOnce() ).findByName( PROVIDED_INGREDIENT_OPTION_NAME ) ;
		
		
		assertNotNull( responseIngredientOptionList, "responseIngredientOptionList is Null." ) ;
		
		assertTrue( responseIngredientOptionList.size() == 1, 
				    "responseIngredientOptionList.size() is " + 
				    responseIngredientOptionList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseIngredientOptionList.get( 0 ).getName().equals( this.NAME_INGREDIENT_OPTION_4 ), 
				    "responseIngredientOptionList.get( 0 ) is " + 
				    responseIngredientOptionList.get( 0 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_OPTION_4 + ")." ) ;
		
		
	}

	

}
