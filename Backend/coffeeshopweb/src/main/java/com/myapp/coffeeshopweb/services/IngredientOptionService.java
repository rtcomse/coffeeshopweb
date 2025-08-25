package com.myapp.coffeeshopweb.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.coffeeshopweb.models.IngredientOption;
import com.myapp.coffeeshopweb.repositories.IngredientOptionRepository;


@Service
public class IngredientOptionService {
	
	
	@Autowired
	private IngredientOptionRepository ingredientOptionRepository ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( IngredientOptionService.class ) ;
	
	
	public List<IngredientOption> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<IngredientOption> retIngredientOptionList = new ArrayList<IngredientOption>() ;
		
		
		retIngredientOptionList = this.ingredientOptionRepository.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retIngredientOptionList ;
		
	}
	
	
	public List<IngredientOption> findById ( String id ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "id: {}", id ) ;
		
		List<IngredientOption> retIngredientOptionList = new ArrayList<IngredientOption>() ;
		
		List<String> idList = new ArrayList<String>() ;
		idList.add( id ) ;
		
		
		retIngredientOptionList = this.ingredientOptionRepository.findAllById( idList ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retIngredientOptionList ;
		
	}
	
	
	public List<IngredientOption> findByName ( String name ) {

		LOGGER.debug( "ENTER: findByName ()" ) ;
		LOGGER.debug( "name: {}", name ) ;

		List<IngredientOption> retIngredientOptionList = new ArrayList<IngredientOption>() ;


		retIngredientOptionList = this.ingredientOptionRepository.findByName( name ) ;


		LOGGER.debug( "EXIT: findByName ()" ) ;

		return retIngredientOptionList ;

	}
	
	
	

}
