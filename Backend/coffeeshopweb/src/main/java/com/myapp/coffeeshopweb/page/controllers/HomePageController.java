package com.myapp.coffeeshopweb.page.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class HomePageController {
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( HomePageController.class ) ;
	
	@GetMapping( "/" )
	public ModelAndView emptyPage () {
		
		LOGGER.debug( "ENTER: emptyPage ()" ) ;
		
		String homePageUrl = "/redirect/server_2/frontend/dist" ;
		
		ModelAndView retModelAndView = new ModelAndView( "redirect:" + homePageUrl ) ;
		
		
		LOGGER.debug( "EXIT: emptyPage ()" ) ;
		
		return retModelAndView ;
		
	}

}
