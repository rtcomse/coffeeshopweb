package com.myapp.coffeeshopweb.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.coffeeshopweb.models.Ingredient;
import com.myapp.coffeeshopweb.repositories.IngredientRepository;


@Service
public class IngredientService {
	

	@Autowired
	private IngredientRepository ingredientRepository ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( IngredientService.class ) ;
	
	
	public List<Ingredient> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<Ingredient> retIngredientList = new ArrayList<Ingredient>() ;
		
		
		retIngredientList = this.ingredientRepository.findByOrderByIdAsc() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retIngredientList ;
		
	}
	
	
	public List<Ingredient> findById ( String id ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "id: {}", id ) ;
		
		List<Ingredient> retIngredientList = new ArrayList<Ingredient>() ;
		
		List<String> idList = new ArrayList<String>() ;
		idList.add( id ) ;
		
		
		retIngredientList = this.ingredientRepository.findAllById( idList ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retIngredientList ;
		
	}
	
	
	public List<Ingredient> findByName ( String name ) {

		LOGGER.debug( "ENTER: findByName ()" ) ;
		LOGGER.debug( "name: {}", name ) ;

		List<Ingredient> retIngredientList = new ArrayList<Ingredient>() ;


		retIngredientList = this.ingredientRepository.findByName( name ) ;


		LOGGER.debug( "EXIT: findByName ()" ) ;

		return retIngredientList ;

	}

}
