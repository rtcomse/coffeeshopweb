package com.myapp.coffeeshopweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.coffeeshopweb.models.IngredientOption;
import com.myapp.coffeeshopweb.services.IngredientOptionService;


@RestController
public class IngredientOptionController {
	
	
	@Autowired
	private IngredientOptionService ingredientOptionService ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( IngredientOptionController.class ) ;
	
	
	@GetMapping( "/ingredient_options" )
	public List<IngredientOption> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<IngredientOption> retIngredientOptionList = new ArrayList<IngredientOption>() ;
		
		
		retIngredientOptionList = this.ingredientOptionService.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retIngredientOptionList ;
		
	}
	
	
	@GetMapping( "/ingredient_options/{ingredientOptionId}" )
	public List<IngredientOption> findById ( @PathVariable String ingredientOptionId ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "ingredientOptionId: {}", ingredientOptionId ) ;
		
		List<IngredientOption> retIngredientOptionList = new ArrayList<IngredientOption>() ;
		
		
		retIngredientOptionList = this.ingredientOptionService.findById( ingredientOptionId ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retIngredientOptionList ;
		
	}
	
	
	@GetMapping( "/ingredient_options/name/{ingredientOptionName}" )
	public List<IngredientOption> findByName ( @PathVariable String ingredientOptionName ) {
		
		LOGGER.debug( "ENTER: findByName ()" ) ;
		LOGGER.debug( "ingredientOptionName: {}", ingredientOptionName ) ;
		
		List<IngredientOption> retIngredientOptionList = new ArrayList<IngredientOption>() ;
		
		
		retIngredientOptionList = this.ingredientOptionService.findByName( ingredientOptionName ) ;
		
		
		LOGGER.debug( "EXIT: findByName ()" ) ;
		
		return retIngredientOptionList ;
		
	}
	
	

}
