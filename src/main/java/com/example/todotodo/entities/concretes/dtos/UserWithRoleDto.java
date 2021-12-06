package com.example.todotodo.entities.concretes.dtos;

import com.example.todotodo.entities.concretes.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRoleDto {
	private Long id;
	private String fullName;
	private String username;
	private String password;
	private Role role;
}
