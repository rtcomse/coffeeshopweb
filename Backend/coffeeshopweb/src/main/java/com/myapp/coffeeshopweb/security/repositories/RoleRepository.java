package com.myapp.coffeeshopweb.security.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.myapp.coffeeshopweb.security.models.Role;


public interface RoleRepository extends ListCrudRepository<Role, String> {
	
	List<Role> findByName ( String name ) ;

}
