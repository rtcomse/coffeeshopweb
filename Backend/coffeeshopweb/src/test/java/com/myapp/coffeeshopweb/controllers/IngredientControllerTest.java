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
import com.myapp.coffeeshopweb.models.Ingredient;
import com.myapp.coffeeshopweb.services.IngredientService;


@WebMvcTest( IngredientController.class )
@WithMockUser
class IngredientControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc ;
	
	@MockitoBean
	private IngredientService ingredientService ;
	
	
	final String NAME_INGREDIENT_1 = "ingredient_1_name" ;
	final String NAME_INGREDIENT_2 = "ingredient_2_name" ;
	final String NAME_INGREDIENT_3 = "ingredient_3_name" ;
	final String NAME_INGREDIENT_4 = "ingredient_4_name" ;
	
	
	
	@BeforeEach
	void beforeEachTest () {
		
		Ingredient ingredient_1 = new Ingredient() ;
		ingredient_1.setId( "1" ) ;
		ingredient_1.setName( this.NAME_INGREDIENT_1 ) ;
		
		Ingredient ingredient_2 = new Ingredient() ;
		ingredient_2.setId( "2" ) ;
		ingredient_2.setName( this.NAME_INGREDIENT_2 ) ;
		
		Ingredient ingredient_3 = new Ingredient() ;
		ingredient_3.setId( "3" ) ;
		ingredient_3.setName( this.NAME_INGREDIENT_3 ) ;
		
		Ingredient ingredient_4 = new Ingredient() ;
		ingredient_4.setId( "4" ) ;
		ingredient_4.setName( this.NAME_INGREDIENT_4 ) ;
		
		
		List<Ingredient> ingredientFindAllList = new ArrayList<Ingredient>() ;
		ingredientFindAllList.add( ingredient_1 ) ;
		ingredientFindAllList.add( ingredient_2 ) ;
		ingredientFindAllList.add( ingredient_3 ) ;
		ingredientFindAllList.add( ingredient_4 ) ;
		
		List<Ingredient> ingredientFindById1List = new ArrayList<Ingredient>() ;
		ingredientFindById1List.add( ingredient_1 ) ;
		
		List<Ingredient> ingredientFindById2List = new ArrayList<Ingredient>() ;
		ingredientFindById2List.add( ingredient_2 ) ;
		
		List<Ingredient> ingredientFindById3List = new ArrayList<Ingredient>() ;
		ingredientFindById3List.add( ingredient_3 ) ;
		
		List<Ingredient> ingredientFindById4List = new ArrayList<Ingredient>() ;
		ingredientFindById4List.add( ingredient_4 ) ;
		
		
		when( this.ingredientService.findAll() ).thenReturn( ingredientFindAllList ) ;
		when( this.ingredientService.findById( ingredient_1.getId() ) ).thenReturn( ingredientFindById1List ) ;
		when( this.ingredientService.findById( ingredient_2.getId() ) ).thenReturn( ingredientFindById2List ) ;		
		when( this.ingredientService.findById( ingredient_3.getId() ) ).thenReturn( ingredientFindById3List ) ;
		when( this.ingredientService.findById( ingredient_4.getId() ) ).thenReturn( ingredientFindById4List ) ;
		
		when( this.ingredientService.findByName( ingredient_1.getName() ) ).thenReturn( ingredientFindById1List ) ;
		when( this.ingredientService.findByName( ingredient_2.getName() ) ).thenReturn( ingredientFindById2List ) ;		
		when( this.ingredientService.findByName( ingredient_3.getName() ) ).thenReturn( ingredientFindById3List ) ;
		when( this.ingredientService.findByName( ingredient_4.getName() ) ).thenReturn( ingredientFindById4List ) ;
		
		
	}

	
	@Test
	void findAllShouldReturnAllIngredients() throws Exception {
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/ingredients" ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Ingredient> responseIngredientList = objectMapper.readValue(responseJson, new TypeReference<List<Ingredient>>(){} ) ;

		
		verify( this.ingredientService, atLeastOnce() ).findAll() ;
		
		
		assertNotNull( responseIngredientList, "responseIngredientList is Null." ) ;
		
		assertTrue( responseIngredientList.size() == 4, 
				    "responseIngredientList.size() is " + 
				    responseIngredientList.size() + 
		            " (Not 4)." ) ;
		
		assertTrue( responseIngredientList.get( 0 ).getName().equals( this.NAME_INGREDIENT_1 ), 
				    "responseIngredientList.get( 0 ) is " + 
				    responseIngredientList.get( 0 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_1 + ")." ) ;
		assertTrue( responseIngredientList.get( 1 ).getName().equals( this.NAME_INGREDIENT_2 ), 
				    "responseIngredientList.get( 1 ) is " + 
				    responseIngredientList.get( 1 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_2 + ")." ) ;
		assertTrue( responseIngredientList.get( 2 ).getName().equals( this.NAME_INGREDIENT_3 ), 
			    	"responseIngredientList.get( 2 ) is " + 
			    	responseIngredientList.get( 2 ).getName() + 
			    	" (not " + this.NAME_INGREDIENT_3 + ")." ) ;
		assertTrue( responseIngredientList.get( 3 ).getName().equals( this.NAME_INGREDIENT_4 ), 
			    	"responseIngredientList.get( 3 ) is " + 
			    	responseIngredientList.get( 3 ).getName() + 
			    	" (not " + this.NAME_INGREDIENT_4 + ")." ) ;

		
		
	}
	
	
	@Test
	void findByIdShouldReturnAllIngredientsHavingProvidedId() throws Exception {
		
		final String PROVIDED_INGREDIENT_ID = "3" ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/ingredients/" + PROVIDED_INGREDIENT_ID ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Ingredient> responseIngredientList = objectMapper.readValue(responseJson, new TypeReference<List<Ingredient>>(){} ) ;

		
		verify( this.ingredientService, atLeastOnce() ).findById( PROVIDED_INGREDIENT_ID ) ;
		
		
		assertNotNull( responseIngredientList, "responseIngredientList is Null." ) ;
		
		assertTrue( responseIngredientList.size() == 1, 
				    "responseIngredientList.size() is " + 
				    responseIngredientList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseIngredientList.get( 0 ).getName().equals( this.NAME_INGREDIENT_3 ), 
				    "responseIngredientList.get( 0 ) is " + 
				    responseIngredientList.get( 0 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_3 + ")." ) ;
		
		
	}
	
	
	@Test
	void findByNameShouldReturnAllIngredientsHavingProvidedName() throws Exception {
		
		final String PROVIDED_INGREDIENT_NAME = this.NAME_INGREDIENT_4 ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/ingredients/name/" + PROVIDED_INGREDIENT_NAME ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<Ingredient> responseIngredientList = objectMapper.readValue(responseJson, new TypeReference<List<Ingredient>>(){} ) ;

		
		verify( this.ingredientService, atLeastOnce() ).findByName( PROVIDED_INGREDIENT_NAME ) ;
		
		
		assertNotNull( responseIngredientList, "responseIngredientList is Null." ) ;
		
		assertTrue( responseIngredientList.size() == 1, 
				    "responseIngredientList.size() is " + 
				    responseIngredientList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseIngredientList.get( 0 ).getName().equals( this.NAME_INGREDIENT_4 ), 
				    "responseIngredientList.get( 0 ) is " + 
				    responseIngredientList.get( 0 ).getName() + 
				    " (not " + this.NAME_INGREDIENT_4 + ")." ) ;
		
		
	}

	

}
