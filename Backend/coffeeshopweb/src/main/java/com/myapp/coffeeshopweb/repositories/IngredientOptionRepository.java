package com.myapp.coffeeshopweb.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.myapp.coffeeshopweb.models.IngredientOption;


public interface IngredientOptionRepository extends ListCrudRepository<IngredientOption, String> {
	
	List<IngredientOption> findByName ( String name ) ;

}
