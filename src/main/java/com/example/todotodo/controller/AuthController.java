package com.example.todotodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todotodo.core.utilities.result.DataResult;
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.UserDto;
import com.example.todotodo.service.abstracts.AuthService;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthService authService;

	

	@PostMapping("/sign-up")
	public ResponseEntity<?> saveUser(@RequestBody UserDto user){
		if(this.authService.findByUsername(user.getUsername()).isPresent()){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(this.authService.saveUser(user) ,HttpStatus.CREATED);
		
	}

	@PostMapping("/sign-in")
	public ResponseEntity<?> loginUser(@RequestBody UserDto user){
		
		DataResult<User> newUser = this.authService.loginUser(user);
		

		if(newUser.getData() != null){
			return ResponseEntity.ok(this.authService.findByUsernameAndPassword(newUser.getData().getUsername(),newUser.getData().getPassword()));
			
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(newUser.getMessage());
			
	}


}
