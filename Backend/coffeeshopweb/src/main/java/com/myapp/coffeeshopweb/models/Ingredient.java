package com.myapp.coffeeshopweb.models;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.ObjectMapper;

@Document( "ingredient" )
public class Ingredient {
	
	@Id
	private String id ;
	
	@Field( "name" )
	private String name ;
	
	@Field( "ingredient_options" )
	private List<IngredientOption> ingredientOptions ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( Ingredient.class ) ;
	
	
	// Getters and Setters:
	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<IngredientOption> getIngredientOptions() {
		return ingredientOptions;
	}


	public void setIngredientOptions(List<IngredientOption> ingredientOptions) {
		this.ingredientOptions = ingredientOptions;
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
