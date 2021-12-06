package com.example.todotodo.repository.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todotodo.entities.concretes.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	List<Todo> findAllByOrderByFdateAsc();
	List<Todo> findAllByOrderByFdateDesc();
	List<Todo> findAllByUser(Long id);
	List<Todo> findByUser_idOrderByFdateAsc(Long id);
	List<Todo> findByUser_idOrderByFdateDesc(Long id);

	List<Todo> getByUser_id(Long id);
}
