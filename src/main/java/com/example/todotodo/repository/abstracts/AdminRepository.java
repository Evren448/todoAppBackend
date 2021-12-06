package com.example.todotodo.repository.abstracts;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.todotodo.entities.concretes.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long>{
	List<User> findAllByOrderByUsernameDesc();
	List<User> findAllByOrderByUsernameAsc();
	List<User> findAllByOrderByFullNameDesc();
	List<User> findAllByOrderByFullNameAsc();

	Optional<User> findByUsername(String username);
	User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
