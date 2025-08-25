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
import com.myapp.coffeeshopweb.models.CoffeeOrder;
import com.myapp.coffeeshopweb.security.services.AccountService;
import com.myapp.coffeeshopweb.services.CoffeeOrderService;
import com.myapp.coffeeshopweb.services.IngredientOptionService;
import com.myapp.coffeeshopweb.services.IngredientService;



@WebMvcTest( CoffeeOrderController.class )
@WithMockUser
class CoffeeOrderControllerTest {
	
	
	@Autowired
	private MockMvc mockMvc ;
	
	@MockitoBean
	private CoffeeOrderService coffeeOrderService ;
	
	@MockitoBean
	private AccountService accountService ;
	@MockitoBean
	private IngredientService ingredientService ;
	@MockitoBean
	private IngredientOptionService ingredientOptionService ;
	
	
	final String ID_COFFEE_ORDER_1 = "1" ;
	final String ID_COFFEE_ORDER_2 = "2" ;
	final String ID_COFFEE_ORDER_3 = "3" ;
	final String ID_COFFEE_ORDER_4 = "4" ;
	
	
	
	@BeforeEach
	void beforeEachTest () {
		
		
		CoffeeOrder coffee_order_1 = new CoffeeOrder() ;
		coffee_order_1.setId( this.ID_COFFEE_ORDER_1 ) ;
		coffee_order_1.setOrderNumber( Integer.valueOf( 1 ) ) ;
		coffee_order_1.setOrderedByUsername( "coffee_order_1_ordered_by_username" ) ;
		
		CoffeeOrder coffee_order_2 = new CoffeeOrder() ;
		coffee_order_2.setId( this.ID_COFFEE_ORDER_2 ) ;
		coffee_order_2.setOrderNumber( Integer.valueOf( 2 ) ) ;
		coffee_order_2.setOrderedByUsername( "coffee_order_2_ordered_by_username" ) ;
		
		CoffeeOrder coffee_order_3 = new CoffeeOrder() ;
		coffee_order_3.setId( this.ID_COFFEE_ORDER_3 ) ;
		coffee_order_3.setOrderNumber( Integer.valueOf( 3 ) ) ;
		coffee_order_3.setOrderedByUsername( "coffee_order_3_ordered_by_username" ) ;
		
		CoffeeOrder coffee_order_4 = new CoffeeOrder() ;
		coffee_order_4.setId( this.ID_COFFEE_ORDER_4 ) ;
		coffee_order_4.setOrderNumber( Integer.valueOf( 4 ) ) ;
		coffee_order_4.setOrderedByUsername( "coffee_order_4_ordered_by_username" ) ;
		
		
		List<CoffeeOrder> coffeeOrderFindAllList = new ArrayList<CoffeeOrder>() ;
		coffeeOrderFindAllList.add( coffee_order_1 ) ;
		coffeeOrderFindAllList.add( coffee_order_2 ) ;
		coffeeOrderFindAllList.add( coffee_order_3 ) ;
		coffeeOrderFindAllList.add( coffee_order_4 ) ;
		
		List<CoffeeOrder> coffeeOrderFindById1List = new ArrayList<CoffeeOrder>() ;
		coffeeOrderFindById1List.add( coffee_order_1 ) ;
		
		List<CoffeeOrder> coffeeOrderFindById2List = new ArrayList<CoffeeOrder>() ;
		coffeeOrderFindById2List.add( coffee_order_2 ) ;
		
		List<CoffeeOrder> coffeeOrderFindById3List = new ArrayList<CoffeeOrder>() ;
		coffeeOrderFindById3List.add( coffee_order_3 ) ;
		
		List<CoffeeOrder> coffeeOrderFindById4List = new ArrayList<CoffeeOrder>() ;
		coffeeOrderFindById4List.add( coffee_order_4 ) ;
		
		
		
		when( this.coffeeOrderService.findAll() ).thenReturn( coffeeOrderFindAllList ) ;
		when( this.coffeeOrderService.findById( coffee_order_1.getId() ) ).thenReturn( coffeeOrderFindById1List ) ;
		when( this.coffeeOrderService.findById( coffee_order_2.getId() ) ).thenReturn( coffeeOrderFindById2List ) ;		
		when( this.coffeeOrderService.findById( coffee_order_3.getId() ) ).thenReturn( coffeeOrderFindById3List ) ;
		when( this.coffeeOrderService.findById( coffee_order_4.getId() ) ).thenReturn( coffeeOrderFindById4List ) ;
		
		when( this.coffeeOrderService.findByOrderNumber( coffee_order_1.getOrderNumber() ) ).thenReturn( coffeeOrderFindById1List ) ;
		when( this.coffeeOrderService.findByOrderNumber( coffee_order_2.getOrderNumber() ) ).thenReturn( coffeeOrderFindById2List ) ;		
		when( this.coffeeOrderService.findByOrderNumber( coffee_order_3.getOrderNumber() ) ).thenReturn( coffeeOrderFindById3List ) ;
		when( this.coffeeOrderService.findByOrderNumber( coffee_order_4.getOrderNumber() ) ).thenReturn( coffeeOrderFindById4List ) ;
		
		when( this.coffeeOrderService.findByOrderedByUsername( "coffee_order_1_ordered_by_username" ) ).thenReturn( coffeeOrderFindById1List ) ;
		when( this.coffeeOrderService.findByOrderedByUsername( "coffee_order_2_ordered_by_username" ) ).thenReturn( coffeeOrderFindById2List ) ;		
		when( this.coffeeOrderService.findByOrderedByUsername( "coffee_order_3_ordered_by_username" ) ).thenReturn( coffeeOrderFindById3List ) ;
		when( this.coffeeOrderService.findByOrderedByUsername( "coffee_order_4_ordered_by_username" ) ).thenReturn( coffeeOrderFindById4List ) ;
		
		
	}

	
	@Test
	void findAllShouldReturnAllCoffeeOrders() throws Exception {
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/coffee_orders" ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<CoffeeOrder> responseCoffeeOrderList = objectMapper.readValue(responseJson, new TypeReference<List<CoffeeOrder>>(){} ) ;

		
		verify( this.coffeeOrderService, atLeastOnce() ).findAll() ;
		
		
		assertNotNull( responseCoffeeOrderList, "responseCoffeeOrderList is Null." ) ;
		
		assertTrue( responseCoffeeOrderList.size() == 4, 
				    "responseCoffeeOrderList.size() is " + 
				    responseCoffeeOrderList.size() + 
		            " (Not 4)." ) ;
		
		assertTrue( responseCoffeeOrderList.get( 0 ).getId().equals( this.ID_COFFEE_ORDER_1 ), 
				    "responseCoffeeOrderList.get( 0 ).getId() is " + 
				    responseCoffeeOrderList.get( 0 ).getId() + 
				    " (not " + this.ID_COFFEE_ORDER_1 + ")." ) ;
		assertTrue( responseCoffeeOrderList.get( 1 ).getId().equals( this.ID_COFFEE_ORDER_2 ), 
				    "responseCoffeeOrderList.get( 1 ).getId() is " + 
				    responseCoffeeOrderList.get( 1 ).getId() + 
				    " (not " + this.ID_COFFEE_ORDER_2 + ")." ) ;
		assertTrue( responseCoffeeOrderList.get( 2 ).getId().equals( this.ID_COFFEE_ORDER_3 ), 
			    "responseCoffeeOrderList.get( 2 ).getId() is " + 
			    responseCoffeeOrderList.get( 2 ).getId() + 
			    " (not " + this.ID_COFFEE_ORDER_3 + ")." ) ;
		assertTrue( responseCoffeeOrderList.get( 3 ).getId().equals( this.ID_COFFEE_ORDER_4 ), 
			    "responseCoffeeOrderList.get( 3 ).getId() is " + 
			    responseCoffeeOrderList.get( 3 ).getId() + 
			    " (not " + this.ID_COFFEE_ORDER_4 + ")." ) ;

		
		
	}
	
	
	@Test
	void findByIdShouldReturnAllCoffeeOrdersHavingProvidedId() throws Exception {
		
		final String PROVIDED_COFFEE_ORDER_ID = "3" ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/coffee_orders/" + PROVIDED_COFFEE_ORDER_ID ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<CoffeeOrder> responseCoffeeOrderList = objectMapper.readValue(responseJson, new TypeReference<List<CoffeeOrder>>(){} ) ;

		
		verify( this.coffeeOrderService, atLeastOnce() ).findById( PROVIDED_COFFEE_ORDER_ID ) ;
		
		
		assertNotNull( responseCoffeeOrderList, "responseCoffeeOrderList is Null." ) ;
		
		assertTrue( responseCoffeeOrderList.size() == 1, 
				    "responseCoffeeOrderList.size() is " + 
				    responseCoffeeOrderList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseCoffeeOrderList.get( 0 ).getId().equals( this.ID_COFFEE_ORDER_3 ), 
				    "responseCoffeeOrderList.get( 0 ).getId() is " + 
				    responseCoffeeOrderList.get( 0 ).getId() + 
				    " (not " + this.ID_COFFEE_ORDER_3 + ")." ) ;
		
		
	}
	
	

	@Test
	void findByOrderNumberShouldReturnAllCoffeeOrdersHavingProvidedOrderNumber() throws Exception {
		
		final Integer PROVIDED_ORDER_NUMBER = Integer.valueOf( 4 ) ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/coffee_orders/order_number/" + PROVIDED_ORDER_NUMBER ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<CoffeeOrder> responseCoffeeOrderList = objectMapper.readValue(responseJson, new TypeReference<List<CoffeeOrder>>(){} ) ;

		
		verify( this.coffeeOrderService, atLeastOnce() ).findByOrderNumber( PROVIDED_ORDER_NUMBER ) ;
		
		
		assertNotNull( responseCoffeeOrderList, "responseCoffeeOrderList is Null." ) ;
		
		assertTrue( responseCoffeeOrderList.size() == 1, 
				    "responseCoffeeOrderList.size() is " + 
				    responseCoffeeOrderList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseCoffeeOrderList.get( 0 ).getId().equals( this.ID_COFFEE_ORDER_4 ), 
				    "responseCoffeeOrderList.get( 0 ).getId() is " + 
				    responseCoffeeOrderList.get( 0 ).getId() + 
				    " (not " + this.ID_COFFEE_ORDER_4 + ")." ) ;
		
		
	}
	
	
	@Test
	void findByOrderedByUsernameShouldReturnAllCoffeeOrdersHavingProvidedOrderedByUsername() throws Exception {
		
		final String PROVIDED_ORDERED_BY_USERNAME = "coffee_order_4_ordered_by_username" ;
		
		MvcResult mvcResult = this.mockMvc.perform( get( "/coffee_orders/ordered_by_username/" + PROVIDED_ORDERED_BY_USERNAME ) )
				                          .andDo( print() )
		                                  .andExpect( status().isOk() )
		                                  .andReturn() ;
		
		String responseJson = mvcResult.getResponse().getContentAsString() ;
		
		ObjectMapper objectMapper = new ObjectMapper() ;
		
		List<CoffeeOrder> responseCoffeeOrderList = objectMapper.readValue(responseJson, new TypeReference<List<CoffeeOrder>>(){} ) ;

		
		verify( this.coffeeOrderService, atLeastOnce() ).findByOrderedByUsername( PROVIDED_ORDERED_BY_USERNAME ) ;
		
		
		assertNotNull( responseCoffeeOrderList, "responseCoffeeOrderList is Null." ) ;
		
		assertTrue( responseCoffeeOrderList.size() == 1, 
				    "responseCoffeeOrderList.size() is " + 
				    responseCoffeeOrderList.size() + 
		            " (Not 1)." ) ;
		assertTrue( responseCoffeeOrderList.get( 0 ).getId().equals( this.ID_COFFEE_ORDER_4 ), 
				    "responseCoffeeOrderList.get( 0 ).getId() is " + 
				    responseCoffeeOrderList.get( 0 ).getId() + 
				    " (not " + this.ID_COFFEE_ORDER_4 + ")." ) ;
		
		
	}
	
	

}
