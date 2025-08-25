package com.myapp.coffeeshopweb.security.config.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.coffeeshopweb.project.ProjectData ;


@RestController
public class FormController {
	
	
	@PostMapping( "/login" )
	public ModelAndView loginFormSubmit () {
		
		ModelAndView retModelAndView = new ModelAndView( "forward:" + 
		                                                 ( new ProjectData() ).getUrlPrefixServer1() + 
		                                                 "/login" ) ;
		
		return retModelAndView ;
		
	}

}
