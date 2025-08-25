package com.myapp.coffeeshopweb.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.coffeeshopweb.models.CoffeeOrder;
import com.myapp.coffeeshopweb.repositories.CoffeeOrderRepository;


@Service
public class CoffeeOrderService {
	

	@Autowired
	private CoffeeOrderRepository coffeeOrderRepository ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( CoffeeOrderService.class ) ;
	
	
	public List<CoffeeOrder> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		retCoffeeOrderList = this.coffeeOrderRepository.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retCoffeeOrderList ;
		
	}
	
	
	public List<CoffeeOrder> findById ( String id ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "id: {}", id ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		List<String> idList = new ArrayList<String>() ;
		idList.add( id ) ;
		
		
		retCoffeeOrderList = this.coffeeOrderRepository.findAllById( idList ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retCoffeeOrderList ;
		
	}
	
	
	public List<CoffeeOrder> findByOrderNumber ( Integer orderNumber ) {

		LOGGER.debug( "ENTER: findByOrderNumber ()" ) ;
		LOGGER.debug( "orderNumber: {}", orderNumber ) ;

		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;


		retCoffeeOrderList = this.coffeeOrderRepository.findByOrderNumber( orderNumber ) ;


		LOGGER.debug( "EXIT: findByOrderNumber ()" ) ;

		return retCoffeeOrderList ;

	}
	
	
	public List<CoffeeOrder> findByOrderedByUsername ( String username ) {

		LOGGER.debug( "ENTER: findByOrderedByUsername ()" ) ;
		LOGGER.debug( "username: {}", username ) ;

		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;


		retCoffeeOrderList = this.coffeeOrderRepository.findByOrderedByUsernameOrderByOrderNumberAsc( username ) ;


		LOGGER.debug( "EXIT: findByOrderedByUsername ()" ) ;

		return retCoffeeOrderList ;

	}
	
	
	public List<CoffeeOrder> deleteByOrderedByUsername ( String username ) {

		LOGGER.debug( "ENTER: deleteByOrderedByUsername ()" ) ;
		LOGGER.debug( "username: {}", username ) ;

		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;


		retCoffeeOrderList = this.findByOrderedByUsername( username ) ;
		
		this.coffeeOrderRepository.deleteAll( retCoffeeOrderList ) ;


		LOGGER.debug( "EXIT: deleteByOrderedByUsername ()" ) ;

		return retCoffeeOrderList ;

	}
	
	
	public List<CoffeeOrder> save ( CoffeeOrder inputCoffeeOrder ) {

		LOGGER.debug( "ENTER: save ()" ) ;
		LOGGER.debug( "inputCoffeeOrder: {}", inputCoffeeOrder ) ;

		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		String inputUsername = inputCoffeeOrder.getOrderedByUsername() ;
		Integer inputOrderNumber = Integer.valueOf( 1 ) ;
		
		List<CoffeeOrder> fullCoffeeOrderList = this.findAll() ;
		
		if ( fullCoffeeOrderList.size() > 0 ) {
			
			Integer maxOrderNumber = fullCoffeeOrderList.getFirst().getOrderNumber() ;
			
			for ( int i = 0 ; i < fullCoffeeOrderList.size() ; i++ ) {
				
				if ( fullCoffeeOrderList.get( i ).getOrderNumber() > maxOrderNumber ) {
					
					maxOrderNumber = fullCoffeeOrderList.get( i ).getOrderNumber() ;
					
				}
				
			}
			
			inputOrderNumber = Integer.valueOf( maxOrderNumber.intValue() + 1 ) ;
			
		}
		
		inputCoffeeOrder.setOrderNumber( inputOrderNumber ) ;
		
		
		
		CoffeeOrder savedCoffeeOrder = this.coffeeOrderRepository.save( inputCoffeeOrder ) ;
		
		retCoffeeOrderList.add( savedCoffeeOrder ) ;
		
		
		final int MAX_NUM_ORDERS = 10 ;
		
		List<CoffeeOrder> matchedCoffeeOrderList = this.findByOrderedByUsername( inputUsername ) ;
		
		Collections.sort( matchedCoffeeOrderList , ( coffeeOrder1, coffeeOrder2 ) -> ( coffeeOrder1.getOrderNumber() == coffeeOrder2.getOrderNumber() ) ?
																						0 : 
																						( coffeeOrder1.getOrderNumber() < coffeeOrder2.getOrderNumber() ) ?
																								-1 : 
																								1 ) ;
		
		if ( matchedCoffeeOrderList.size() > MAX_NUM_ORDERS ) {
			
			int numOrdersToDelete = matchedCoffeeOrderList.size() - MAX_NUM_ORDERS ;
			
			List<CoffeeOrder> deleteCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
			
			for ( int i = 0 ; i < numOrdersToDelete ; i++ ) {
				
				deleteCoffeeOrderList.add( matchedCoffeeOrderList.get( i ) ) ;
				
			}
			
			this.coffeeOrderRepository.deleteAll( deleteCoffeeOrderList ) ;
			
		}
		
		


		LOGGER.debug( "EXIT: save ()" ) ;

		return retCoffeeOrderList ;

	}

}
