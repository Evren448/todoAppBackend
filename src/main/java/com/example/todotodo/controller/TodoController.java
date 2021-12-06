package com.example.todotodo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.todotodo.entities.concretes.dtos.TodoItemDto;
import com.example.todotodo.service.abstracts.TodoService;

@RestController
@RequestMapping("/api/todo")
@CrossOrigin
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("todo")
	public ResponseEntity<?> getAllTodo(){
		return ResponseEntity.ok(this.todoService.findAll());
	}
	
	@GetMapping("todo/desc")
	public ResponseEntity<?> getAllTodoByDateDesc(){
		return ResponseEntity.ok(this.todoService.findAllByDateDesc());
	}
	
	@GetMapping("todo/asc")
	public ResponseEntity<?> getAllTodoByDateAsc(){
		return ResponseEntity.ok(this.todoService.findAllByDateAsc());
	}
	
	@GetMapping("todo/desc/{id}")
	public ResponseEntity<?> getUserTodosByDateDesc(@PathVariable Long id){
		return ResponseEntity.ok(this.todoService.getByUser_idOrderByDateDESC(id));
	}
	
	@GetMapping("todo/asc/{id}")
	public ResponseEntity<?> getUserTodosByDateAsc(@PathVariable Long id){
		return ResponseEntity.ok(this.todoService.getByUser_idOrderByDateASC(id));
	}

	@GetMapping("todo/user/{id}")
	public ResponseEntity<?> getAllUsersTodo(@PathVariable Long id){
		return ResponseEntity.ok(this.todoService.getByUser_id(id));
	}
	
	@PostMapping("todo")
	public ResponseEntity<?> addTodo(@RequestBody TodoItemDto todo) {
		return ResponseEntity.ok(this.todoService.addTodo(todo));
	}
	
	@PutMapping("todo")
	public ResponseEntity<?> updateTodo(@RequestBody TodoItemDto todo) {
		return ResponseEntity.ok(this.todoService.updateTodo(todo));
	}
	
	@DeleteMapping("todo/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable Long id) {
		return ResponseEntity.ok(this.todoService.deleteTodo(id));
	}
}
