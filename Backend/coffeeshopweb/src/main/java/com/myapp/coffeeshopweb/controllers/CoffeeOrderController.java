package com.myapp.coffeeshopweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.coffeeshopweb.models.CoffeeOrder;
import com.myapp.coffeeshopweb.models.Ingredient;
import com.myapp.coffeeshopweb.models.IngredientOption;
import com.myapp.coffeeshopweb.security.models.Account;
import com.myapp.coffeeshopweb.security.services.AccountService;
import com.myapp.coffeeshopweb.services.CoffeeOrderService;
import com.myapp.coffeeshopweb.services.IngredientOptionService;
import com.myapp.coffeeshopweb.services.IngredientService;


@RestController
public class CoffeeOrderController {
	
	
	@Autowired
	private CoffeeOrderService coffeeOrderService ;
	
	@Autowired
	private AccountService accountService ;
	@Autowired
	private IngredientService ingredientService ;
	@Autowired
	private IngredientOptionService ingredientOptionService ;
	
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( CoffeeOrderController.class ) ;
	
	
	@GetMapping( "/coffee_orders" )
	public List<CoffeeOrder> findAll () {
		
		LOGGER.debug( "ENTER: findAll ()" ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		retCoffeeOrderList = this.coffeeOrderService.findAll() ;
		
		
		LOGGER.debug( "EXIT: findAll ()" ) ;
		
		return retCoffeeOrderList ;
		
	}
	
	
	@GetMapping( "/coffee_orders/{coffeeOrderId}" )
	public List<CoffeeOrder> findById ( @PathVariable String coffeeOrderId ) {
		
		LOGGER.debug( "ENTER: findById ()" ) ;
		LOGGER.debug( "coffeeOrderId: {}", coffeeOrderId ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		retCoffeeOrderList = this.coffeeOrderService.findById( coffeeOrderId ) ;
		
		
		LOGGER.debug( "EXIT: findById ()" ) ;
		
		return retCoffeeOrderList ;
		
	}
	
	
	@GetMapping( "/coffee_orders/order_number/{orderNumber}" )
	public List<CoffeeOrder> findByOrderNumber ( @PathVariable Integer orderNumber ) {
		
		LOGGER.debug( "ENTER: findByOrderNumber ()" ) ;
		LOGGER.debug( "orderNumber: {}", orderNumber ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		retCoffeeOrderList = this.coffeeOrderService.findByOrderNumber( orderNumber ) ;
		
		
		LOGGER.debug( "EXIT: findByOrderNumber ()" ) ;
		
		return retCoffeeOrderList ;
		
	}
	
	
	@GetMapping( "/coffee_orders/ordered_by_username/{username}" )
	public List<CoffeeOrder> findByOrderedByUsername ( @PathVariable String username ) {
		
		LOGGER.debug( "ENTER: findByOrderedByUsername ()" ) ;
		LOGGER.debug( "username: {}", username ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		retCoffeeOrderList = this.coffeeOrderService.findByOrderedByUsername( username ) ;
		
		
		LOGGER.debug( "EXIT: findByOrderedByUsername ()" ) ;
		
		return retCoffeeOrderList ;
		
	}
	
	@PostMapping( "/coffee_orders/order_placement" )
	public ModelAndView orderPlacement ( @RequestParam( name = "selectedIngredientOptionList", 
														defaultValue = "" ) List<String> selectedIngredientOptionList ) {
		
		LOGGER.debug( "ENTER: orderPlacement ()" ) ;
		LOGGER.debug( "selectedIngredientOptionList: {}", selectedIngredientOptionList ) ;
		
		ModelAndView retFailureModelAndView = new ModelAndView( "redirect:/redirect/server_2/frontend/dist/order_failure_page" ) ;	
		
		if ( selectedIngredientOptionList.size() == 0 ) {

			LOGGER.error( "selectedIngredientOptionList is empty." ) ;
			LOGGER.debug( "EXIT: orderPlacement ()" ) ;

			return retFailureModelAndView ;

		}
		
		
		List<Account> matchedAccountList = new ArrayList<Account>() ;
		
		List<Account> guestAccountList = this.accountService.findByGuest() ;
		
		if ( guestAccountList.size() > 0 ) {
			
			matchedAccountList = guestAccountList ;
			
		}
		
		List<Account> authenticatedAccountList = this.accountService.findByAuthenticated() ;
		
		if ( authenticatedAccountList.size() > 0 ) {
			
			matchedAccountList = authenticatedAccountList ;
			
		}
		
		
		if ( matchedAccountList.size() == 0 ) {
			
			LOGGER.error( "Could not find account for order placement." ) ;
			LOGGER.debug( "EXIT: orderPlacement ()" ) ;
			
			return retFailureModelAndView ;
			
		}
		
		Account matchedAccount = matchedAccountList.getFirst() ;
		
		
		
		List<String> selectedIngredientIdList = new ArrayList<String>() ;
		List<String> selectedIngredientOptionIdList = new ArrayList<String>() ;
		
		for ( int i = 0 ; i < selectedIngredientOptionList.size() ; i++ ) {
			
			String[] selectedIds = selectedIngredientOptionList.get( i ).split( "_DELIMITER_" ) ;
			
			selectedIngredientIdList.add( selectedIds[ 0 ] ) ;
			selectedIngredientOptionIdList.add( selectedIds[ 1 ] ) ;
			
		}
		
		
		CoffeeOrder currCoffeeOrder = new CoffeeOrder() ;
		
		currCoffeeOrder.setId( null ) ;
		currCoffeeOrder.setOrderedByUsername( matchedAccount.getUsername() ) ;
		currCoffeeOrder.setSelectedIngredients( new ArrayList<Ingredient>() ) ;
		currCoffeeOrder.setSelectedIngredientOptions( new ArrayList<IngredientOption>() ) ;
		
		for ( int i = 0 ; i < selectedIngredientOptionList.size() ; i++ ) {
			
			List<Ingredient> matchedIngredientList = this.ingredientService.findById( selectedIngredientIdList.get( i ) ) ;
			
			if ( matchedIngredientList.size() == 0 ) {
				
				LOGGER.error( "Could not find ingredient with id: {}", selectedIngredientIdList.get( i ) ) ;
				LOGGER.debug( "EXIT: orderPlacement ()" ) ;
				
				return retFailureModelAndView ;
				
			}
			
			Ingredient matchedIngredient = matchedIngredientList.getFirst() ;
			
			
			List<IngredientOption> matchedIngredientOptionList = this.ingredientOptionService.findById( selectedIngredientOptionIdList.get( i ) ) ;
			
			if (  matchedIngredientOptionList.size() == 0 ) {

				LOGGER.error( "Could not find ingredient option with id: {}", selectedIngredientOptionIdList.get( i ) ) ;
				LOGGER.debug( "EXIT: orderPlacement ()" ) ;

				return retFailureModelAndView ;

			}
			
			IngredientOption matchedIngredientOption = matchedIngredientOptionList.getFirst() ;
			
			
			
			boolean foundIngredient = false ;
			
			for ( int j = 0 ; j < currCoffeeOrder.getSelectedIngredients().size() ; j++ ) {
				
				if ( currCoffeeOrder.getSelectedIngredients().get( j ).getId().equals( selectedIngredientIdList.get( i ) ) ) {
					
					foundIngredient = true ;
					break ;
					
				}
				
			}
			
			if ( !( foundIngredient ) ) {
				
				currCoffeeOrder.getSelectedIngredients().add( matchedIngredient ) ;
				
			}
			
			
			currCoffeeOrder.getSelectedIngredientOptions().add( matchedIngredientOption ) ;
			
		}
		
		List<CoffeeOrder> createdCoffeeOrderList = this.coffeeOrderService.save( currCoffeeOrder ) ;
		
		if (  createdCoffeeOrderList.size() == 0 ) {

			LOGGER.error( "Could not create new CoffeeOrder." ) ;
			LOGGER.debug( "EXIT: orderPlacement ()" ) ;

			return retFailureModelAndView ;

		}
		
		
		
		ModelAndView retSuccessModelAndView = new ModelAndView( "redirect:/redirect/server_2/frontend/dist/order_success_page/" + 
																createdCoffeeOrderList.getFirst().getOrderNumber() ) ;	
		
		
		LOGGER.debug( "EXIT: orderPlacement ()" ) ;
		
		return retSuccessModelAndView ;
		
	}
	
	
	@GetMapping( "/coffee_orders/order_history" )
	public List<CoffeeOrder> orderHistory () {
		
		LOGGER.debug( "ENTER: orderHistory ()" ) ;
		
		List<CoffeeOrder> retCoffeeOrderList = new ArrayList<CoffeeOrder>() ;
		
		
		List<Account> matchedAccountList = new ArrayList<Account>() ;
		
		List<Account> guestAccountList = this.accountService.findByGuest() ;
		
		if ( guestAccountList.size() > 0 ) {
			
			matchedAccountList = guestAccountList ;
			
		}
		
		List<Account> authenticatedAccountList = this.accountService.findByAuthenticated() ;
		
		if ( authenticatedAccountList.size() > 0 ) {
			
			matchedAccountList = authenticatedAccountList ;
			
		}
		
		
		if ( matchedAccountList.size() == 0 ) {
			
			LOGGER.error( "Could not find account for order history." ) ;
			LOGGER.debug( "EXIT: orderHistory ()" ) ;
			
			return retCoffeeOrderList ;
			
		}
		
		Account matchedAccount = matchedAccountList.getFirst() ;
		
		
		retCoffeeOrderList = this.coffeeOrderService.findByOrderedByUsername( matchedAccount.getUsername() ) ;
		
		
		
		LOGGER.debug( "EXIT: orderHistory ()" ) ;
		
		return retCoffeeOrderList ;
		
	}

}
