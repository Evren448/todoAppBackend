package com.example.todotodo.service.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todotodo.core.utilities.result.DataResult;
import com.example.todotodo.core.utilities.result.DataSuccessResult;
import com.example.todotodo.core.utilities.result.ErrorDataResult;
import com.example.todotodo.core.utilities.result.Result;
import com.example.todotodo.core.utilities.result.SuccessResult;
import com.example.todotodo.entities.concretes.Todo;
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.TodoItemDto;
import com.example.todotodo.repository.abstracts.TodoRepository;
import com.example.todotodo.repository.abstracts.UserRepository;
import com.example.todotodo.service.abstracts.TodoService;

@Service
public class TodoManager implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public DataResult<TodoItemDto> addTodo(TodoItemDto todo) {
		User tmpUser = this.userRepository.findById(todo.getUser_id()).orElse(null);
		Todo newTodo = new Todo();
		TodoItemDto dto = new TodoItemDto();
		
		if(tmpUser != null) {
			newTodo.setCreatedDate(todo.getCreatedDate());
			newTodo.setFdate(todo.getFdate());
			newTodo.setDescription(todo.getDescription());
			newTodo.setStatus(todo.getStatus());
			newTodo.setUser(this.userRepository.findById(todo.getUser_id()).orElse(null));
			
			this.todoRepository.save(newTodo);
			dto.setCreatedDate(newTodo.getCreatedDate());
			dto.setFdate(newTodo.getFdate());
			dto.setDescription(newTodo.getDescription());
			dto.setStatus(newTodo.getStatus());
			dto.setFullName(newTodo.getUser().getFullName());
			dto.setUser_id(newTodo.getUser().getId());
			dto.setId(newTodo.getId());
			
				
			return new DataSuccessResult<TodoItemDto>(dto, "Todo eklendi.");

		}
		throw new UsernameNotFoundException("Boyle bir kullanici yok.");
		
		
	}

	@Override
	public DataResult<TodoItemDto> updateTodo(TodoItemDto todo) {
		User tmpUser = this.userRepository.findById(todo.getUser_id()).orElse(null);
		Todo newTodo = this.todoRepository.findById(todo.getId()).orElse(null);
		TodoItemDto dto = new TodoItemDto();
		
		if(newTodo != null) {
			newTodo.setCreatedDate(todo.getCreatedDate());
			newTodo.setFdate(todo.getFdate());
			newTodo.setDescription(todo.getDescription());
			newTodo.setStatus(todo.getStatus());
			newTodo.setUser(tmpUser);
			newTodo.setId(todo.getId());
			
			this.todoRepository.save(newTodo);
			
			dto.setCreatedDate(newTodo.getCreatedDate());
			dto.setFdate(newTodo.getFdate());
			dto.setDescription(newTodo.getDescription());
			dto.setStatus(newTodo.getStatus());
			dto.setFullName(newTodo.getUser().getFullName());
			dto.setUser_id(newTodo.getUser().getId());
			dto.setId(newTodo.getId());
			
				
			return new DataSuccessResult<TodoItemDto>(dto, "Todo eklendi.");
			
			
		}
		return new ErrorDataResult<>("Boyle bir kullanici yok.");
	}

	@Override
	public Result deleteTodo(Long id) {
		this.todoRepository.deleteById(id);
		return new SuccessResult("Todo silindi.");
	}

	@Override
	public DataResult<List<TodoItemDto>> findAll() {
		List<TodoItemDto> todoList = new ArrayList<TodoItemDto>();
		for (Todo item : this.todoRepository.findAll()) {
			TodoItemDto dto = new TodoItemDto();
			dto.setCreatedDate(item.getCreatedDate());
			dto.setFdate(item.getFdate());
			dto.setDescription(item.getDescription());
			dto.setStatus(item.getStatus());
			dto.setUser_id(item.getUser().getId());
			dto.setId(item.getId());
			
			dto.setFullName(item.getUser().getFullName());
			
			todoList.add(dto);
		}
		
		return new DataSuccessResult<List<TodoItemDto>>(todoList, "Butun todolar listelendi.");
	}

	
	@Override
	public DataResult<List<Todo>> findByUser(Long id) {
		return new DataSuccessResult<List<Todo>>(this.todoRepository.findAllByUser(id));
	}

	@Override
	public DataResult<List<TodoItemDto>> getByUser_id(Long id) {
		List<TodoItemDto> todoList = new ArrayList<TodoItemDto>();
		List<Todo> items = this.todoRepository.getByUser_id(id);
		for (Todo item : items) {
			TodoItemDto dto = new TodoItemDto();
			dto.setCreatedDate(item.getCreatedDate());
			dto.setFdate(item.getFdate());
			dto.setDescription(item.getDescription());
			dto.setStatus(item.getStatus());
			dto.setUser_id(item.getUser().getId());
			dto.setId(item.getId());
			dto.setFullName(item.getUser().getFullName());
			todoList.add(dto);
		}
		
		return new DataSuccessResult<List<TodoItemDto>>(todoList, "Butun todolar listelendi.");
		
	}

	@Override
	public DataResult<List<TodoItemDto>> findAllByDateDesc() {
		List<TodoItemDto> todoList = new ArrayList<TodoItemDto>();
		List<Todo> items = this.todoRepository.findAllByOrderByFdateDesc();
		for (Todo item : items) {
			TodoItemDto dto = new TodoItemDto();
			dto.setCreatedDate(item.getCreatedDate());
			dto.setFdate(item.getFdate());
			dto.setDescription(item.getDescription());
			dto.setStatus(item.getStatus());
			dto.setUser_id(item.getUser().getId());
			dto.setId(item.getId());
			dto.setFullName(item.getUser().getFullName());
			todoList.add(dto);
		}
		
		return new DataSuccessResult<List<TodoItemDto>>(todoList, "Butun todolar listelendi.");
	}

	@Override
	public DataResult<List<TodoItemDto>> findAllByDateAsc() {
		List<TodoItemDto> todoList = new ArrayList<TodoItemDto>();
		List<Todo> items = this.todoRepository.findAllByOrderByFdateAsc();
		for (Todo item : items) {
			TodoItemDto dto = new TodoItemDto();
			dto.setCreatedDate(item.getCreatedDate());
			dto.setFdate(item.getFdate());
			dto.setDescription(item.getDescription());
			dto.setStatus(item.getStatus());
			dto.setUser_id(item.getUser().getId());
			dto.setId(item.getId());
			dto.setFullName(item.getUser().getFullName());
			todoList.add(dto);
		}
		
		return new DataSuccessResult<List<TodoItemDto>>(todoList, "Butun todolar listelendi.");


	}

	@Override
	public DataResult<List<TodoItemDto>> getByUser_idOrderByDateASC(Long id) {
		
		List<TodoItemDto> todoList = new ArrayList<TodoItemDto>();
		List<Todo> items = this.todoRepository.findByUser_idOrderByFdateAsc(id);
		for (Todo item : items) {
			TodoItemDto dto = new TodoItemDto();
			dto.setCreatedDate(item.getCreatedDate());
			dto.setFdate(item.getFdate());
			dto.setDescription(item.getDescription());
			dto.setStatus(item.getStatus());
			dto.setUser_id(item.getUser().getId());
			dto.setId(item.getId());
			dto.setFullName(item.getUser().getFullName());
			todoList.add(dto);
		}
		
		return new DataSuccessResult<List<TodoItemDto>>(todoList, "todolar listelendi.");
		
	}

	@Override
	public DataResult<List<TodoItemDto>> getByUser_idOrderByDateDESC(Long id) {
		List<TodoItemDto> todoList = new ArrayList<TodoItemDto>();
		List<Todo> items = this.todoRepository.findByUser_idOrderByFdateDesc(id);
		for (Todo item : items) {
			TodoItemDto dto = new TodoItemDto();
			dto.setCreatedDate(item.getCreatedDate());
			dto.setFdate(item.getFdate());
			dto.setDescription(item.getDescription());
			dto.setStatus(item.getStatus());
			dto.setUser_id(item.getUser().getId());
			dto.setId(item.getId());
			dto.setFullName(item.getUser().getFullName());	
			todoList.add(dto);
		}
		
		return new DataSuccessResult<List<TodoItemDto>>(todoList, "todolar listelendi.");
	}

	
	
}
