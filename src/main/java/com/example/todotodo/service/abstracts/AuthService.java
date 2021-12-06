package com.example.todotodo.service.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.todotodo.core.utilities.result.DataResult;
import com.example.todotodo.core.utilities.result.Result;
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.UserDto;


public interface AuthService extends UserDetailsService{
	Result saveUser(UserDto user);
	
	DataResult<User> loginUser(UserDto user);
	Optional<User> findByUsername(String username);
	DataResult<User> findByUsernameAndPassword(String username, String password);
}
