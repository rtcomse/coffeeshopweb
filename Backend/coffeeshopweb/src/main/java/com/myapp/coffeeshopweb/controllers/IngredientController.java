package com.myapp.coffeeshopweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.coffeeshopweb.models.Ingredient;
import com.myapp.coffeeshopweb.services.IngredientService;


@RestController
public class IngredientController {
	

	@Autowired
	private IngredientService ingredientService ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( IngredientController.class ) ;
	
	
	@GetMapping( "/ingredients" )
	public List<Ingredient> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<Ingredient> retIngredientList = new ArrayList<Ingredient>() ;
		
		
		retIngredientList = this.ingredientService.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retIngredientList ;
		
	}
	
	
	@GetMapping( "/ingredients/{ingredientId}" )
	public List<Ingredient> findById ( @PathVariable String ingredientId ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "ingredientId: {}", ingredientId ) ;
		
		List<Ingredient> retIngredientList = new ArrayList<Ingredient>() ;
		
		
		retIngredientList = this.ingredientService.findById( ingredientId ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retIngredientList ;
		
	}
	
	
	@GetMapping( "/ingredients/name/{ingredientName}" )
	public List<Ingredient> findByName ( @PathVariable String ingredientName ) {
		
		LOGGER.debug( "ENTER: findByName ()" ) ;
		LOGGER.debug( "ingredientName: {}", ingredientName ) ;
		
		List<Ingredient> retIngredientList = new ArrayList<Ingredient>() ;
		
		
		retIngredientList = this.ingredientService.findByName( ingredientName ) ;
		
		
		LOGGER.debug( "EXIT: findByName ()" ) ;
		
		return retIngredientList ;
		
	}

}
