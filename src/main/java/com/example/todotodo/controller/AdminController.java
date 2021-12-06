package com.example.todotodo.controller;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todotodo.entities.concretes.Role;
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.UserWithRoleDto;
import com.example.todotodo.service.abstracts.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/users")
	public ResponseEntity<?> getUsers(){
		return ResponseEntity.ok(this.adminService.getUsers());
	}
	
	
	@GetMapping("/users/usernameDesc")
	public ResponseEntity<?> getUsersUsernameDesc(){
		return ResponseEntity.ok(this.adminService.findAllByUsernameDesc());
	}
	
	@GetMapping("/users/usernameAsc")
	public ResponseEntity<?> getUsersUsernameAsc(){
		return ResponseEntity.ok(this.adminService.findAllByUsernameAsc());
	}
	
	@GetMapping("/users/fullnameDesc")
	public ResponseEntity<?> getUsersFullnameDesc(){
		return ResponseEntity.ok(this.adminService.findAllByFullnameDesc());
	}
	
	@GetMapping("/users/fullnameAsc")
	public ResponseEntity<?> getUsersFullnameAsc(){
		return ResponseEntity.ok(this.adminService.findAllByFullnameAsc());
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody User user){
		if(this.adminService.findByUsername(user.getUsername()).isPresent()){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(this.adminService.addUser(user) ,HttpStatus.CREATED);
			
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		
		if(this.adminService.findByUserId(id).getData().getRole().equals(Role.ADMIN)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.ok(this.adminService.deleteUser(id));
			
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> findUsersById(@PathVariable Long id){
		return ResponseEntity.ok(this.adminService.findByUserId(id));
			
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@RequestBody UserWithRoleDto user){
	
		return ResponseEntity.ok(this.adminService.updateUser(user));

			
	}
	
}
