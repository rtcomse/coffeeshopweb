package com.myapp.coffeeshopweb.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.myapp.coffeeshopweb.models.Ingredient;


public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {
	
	List<Ingredient> findByOrderByIdAsc () ;
	
	List<Ingredient> findByName ( String name ) ;

}
