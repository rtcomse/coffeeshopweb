package com.myapp.coffeeshopweb.security.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.myapp.coffeeshopweb.security.models.Account;


public interface AccountRepository extends ListCrudRepository<Account, String> {
	
	List<Account> findByUsername ( String username ) ;

}
