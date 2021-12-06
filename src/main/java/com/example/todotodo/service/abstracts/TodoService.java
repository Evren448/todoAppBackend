package com.example.todotodo.service.abstracts;

import java.util.List;

import com.example.todotodo.core.utilities.result.DataResult;
import com.example.todotodo.core.utilities.result.Result;
import com.example.todotodo.entities.concretes.Todo;
import com.example.todotodo.entities.concretes.dtos.TodoItemDto;

public interface TodoService {
	DataResult<TodoItemDto> addTodo(TodoItemDto todo);
	DataResult<TodoItemDto> updateTodo(TodoItemDto todo);
	Result deleteTodo(Long id);
	DataResult<List<TodoItemDto>> findAll();
	DataResult<List<TodoItemDto>> findAllByDateDesc();
	DataResult<List<TodoItemDto>> findAllByDateAsc();
	DataResult<List<Todo>> findByUser(Long id);
	DataResult<List<TodoItemDto>> getByUser_id(Long id);
	DataResult<List<TodoItemDto>> getByUser_idOrderByDateASC(Long id);
	DataResult<List<TodoItemDto>> getByUser_idOrderByDateDESC(Long id);

}
