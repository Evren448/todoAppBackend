package com.example.todotodo.service.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.todotodo.core.utilities.result.DataResult;
import com.example.todotodo.core.utilities.result.Result;
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.UserWithRoleDto;

public interface AdminService extends UserDetailsService{
	Result deleteUser(Long id);
	DataResult<User> findByUserId(Long id);
	DataResult<User> updateUser(UserWithRoleDto user);
	DataResult<List<User>> getUsers();
	DataResult<User> addUser(User user);
	Optional<User> findByUsername(String username);
	DataResult<User> findByUsernameAndPassword(String username, String password);
	DataResult<List<User>> findAllByUsernameDesc();
	DataResult<List<User>> findAllByUsernameAsc();
	DataResult<List<User>> findAllByFullnameDesc();
	DataResult<List<User>> findAllByFullnameAsc();
}
