package com.myapp.coffeeshopweb.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ProjectData {
	

	private final String urlPrefix = "/coffeeshopweb" ;
	private final String urlPrefixServer1 = this.urlPrefix + 
			                                "/server_1" ;
	private final String urlPrefixServer2 = this.urlPrefix + 
                                            "/server_2" ;
	
	// LOGGER
	private static final Logger LOGGER = LoggerFactory.getLogger( ProjectData.class ) ;
	
	
	// Getters and Setters:
	
	public String getUrlPrefix() {
		return urlPrefix;
	}

	public String getUrlPrefixServer1() {
		return urlPrefixServer1;
	}

	public String getUrlPrefixServer2() {
		return urlPrefixServer2;
	}
	
	

	// Method: toString():
	
	public String toString () {
		
		String retString = "" ;
		
		try {
			
			ObjectMapper objectMapper = new ObjectMapper() ;
			
			String stringJson = objectMapper.writeValueAsString( this ) ;
			
			
			retString = stringJson ;
			
		} 
		catch ( Exception e ) { 
			
			LOGGER.error( "Exception: {}", e ) ;
			
		}
		
		
		
		return retString ;
		
	}

}
