package com.myapp.coffeeshopweb.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.myapp.coffeeshopweb.models.CoffeeOrder;


public interface CoffeeOrderRepository extends ListCrudRepository<CoffeeOrder, String> {
	
	List<CoffeeOrder> findByOrderNumber ( Integer orderNumber ) ;
	List<CoffeeOrder> findByOrderedByUsernameOrderByOrderNumberAsc ( String username ) ;

}
