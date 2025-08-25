package com.myapp.coffeeshopweb.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

import com.myapp.coffeeshopweb.project.ProjectData ;
import com.myapp.coffeeshopweb.security.config.services.UserDetailsServiceAccount;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	@Value( "${com.myapp.coffeeshopweb.security.csrf.activate}" )
	private String activateCsrf ;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain ( HttpSecurity http ) throws Exception {
		
		Boolean enableCsrf = Boolean.valueOf( this.activateCsrf ) ;
		
		http.authorizeHttpRequests( ( authorize ) -> authorize
													.requestMatchers( "/coffee_orders/order_placement" ).permitAll()
													.requestMatchers( "/coffee_orders/order_history" ).permitAll()
													.requestMatchers( "/coffee_orders/**" ).hasRole( "ADMIN" )
													
													.requestMatchers( "/ingredients" ).permitAll()
													.requestMatchers( "/ingredients/**" ).hasRole( "ADMIN" )
													
													.requestMatchers( "/ingredient_options/**" ).hasRole( "ADMIN" )
													
													.requestMatchers( "/accounts/create_account" ).permitAll()
													.requestMatchers( "/accounts/delete_account/current_user" ).hasRole( "USER" )
													.requestMatchers( "/accounts/**" ).hasRole( "ADMIN" )
													
													.requestMatchers( "/roles/**" ).hasRole( "ADMIN" )
													
													.anyRequest().permitAll() )
		    .httpBasic( Customizer.withDefaults() )
		    .formLogin( ( form ) -> form.loginProcessingUrl( ( new ProjectData() ).getUrlPrefixServer1() + 
		    		                                         "/login" ) )
		    .logout( ( form ) -> form.logoutRequestMatcher( PathPatternRequestMatcher.withDefaults().matcher( HttpMethod.GET , "/custom_logout" ) ) ) ;
		
		
		if ( !( enableCsrf ) ) {
			
			http.csrf( csrf -> csrf.disable() ) ;
			
		}
		
		
		return http.build() ;
		
	}
	
	
	
	@Bean
	public UserDetailsService userDetailsService () {
		
		return new UserDetailsServiceAccount() ;
		
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder () {
		
		PasswordEncoder retPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder() ;
		
		return retPasswordEncoder ;
		
	}
	
	@Bean
	public static RoleHierarchy roleHierarchy () {
		
		return RoleHierarchyImpl.withDefaultRolePrefix()
				                .role( "ADMIN" ).implies( "USER" )
				                .role( "GUEST" ).implies( "USER" )
				                .role( "HELPER" ).implies( "USER" )
				                .build() ;
		
	}

}
