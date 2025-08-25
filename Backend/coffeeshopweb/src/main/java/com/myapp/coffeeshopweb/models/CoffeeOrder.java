package com.myapp.coffeeshopweb.models;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.ObjectMapper;

@Document( "coffee_order" )
public class CoffeeOrder {
	
	@Id
	private String id ;
	
	@Field( "order_number" )
	private Integer orderNumber ;
	
	@Field( "ordered_by_username" )
	private String orderedByUsername ;
	
	
	@Field( "selected_ingredients" )
	private List<Ingredient> selectedIngredients ;
	
	@Field( "selected_ingredient_options" )
	private List<IngredientOption> selectedIngredientOptions ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( CoffeeOrder.class ) ;
	
	
	// Getters and Setters:
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getOrderNumber() {
		return orderNumber;
	}


	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}


	public String getOrderedByUsername() {
		return orderedByUsername;
	}


	public void setOrderedByUsername(String orderedByUsername) {
		this.orderedByUsername = orderedByUsername;
	}

	
	public List<Ingredient> getSelectedIngredients() {
		return selectedIngredients;
	}


	public void setSelectedIngredients(List<Ingredient> selectedIngredients) {
		this.selectedIngredients = selectedIngredients;
	}


	public List<IngredientOption> getSelectedIngredientOptions() {
		return selectedIngredientOptions;
	}


	public void setSelectedIngredientOptions(List<IngredientOption> selectedIngredientOptions) {
		this.selectedIngredientOptions = selectedIngredientOptions;
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
