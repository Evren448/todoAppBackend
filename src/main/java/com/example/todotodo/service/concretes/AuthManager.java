package com.example.todotodo.service.concretes;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todotodo.core.utilities.result.DataResult;
import com.example.todotodo.core.utilities.result.DataSuccessResult;
import com.example.todotodo.core.utilities.result.ErrorDataResult;
import com.example.todotodo.core.utilities.result.ErrorResult;
import com.example.todotodo.core.utilities.result.Result;
import com.example.todotodo.core.utilities.result.SuccessResult;
import com.example.todotodo.entities.concretes.Role;
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.UserDto;
import com.example.todotodo.entities.concretes.dtos.UserWithRoleDto;
import com.example.todotodo.repository.abstracts.UserRepository;
import com.example.todotodo.service.abstracts.AuthService;

@Service
public class AuthManager implements AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
	
	@Override
	public DataResult<User> findByUsernameAndPassword(String username, String password) {
		User newUser = this.userRepository.findByUsername(username).orElse(null);
	 
		if(newUser == null) {
			return new ErrorDataResult<>("Giris basarisiz.");
		}
		
		if(newUser.getPassword().equals(password)) {
			return new DataSuccessResult<User>(this.userRepository.findByUsernameAndPassword(username, password), "Basariyla giris yapildi.");
	
		}
		return new ErrorDataResult<>("Giris basarisiz.");
	
	}

	@Override
	public Result saveUser(UserDto user) {
		User newUser = new User();
		newUser.setFullName(user.getFullName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setUsername(user.getUsername());
		newUser.setRole(Role.USER);
		
		this.userRepository.save(newUser);
		return new SuccessResult("User kaydedildi.");
		
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User cannot found!"));
        
	}

	


	@Override
	public DataResult<User> loginUser(UserDto user) {
		
		User loginedUser = this.userRepository.findByUsername(user.getUsername()).orElse(null);
		
		
		if(loginedUser == null) {
			return new ErrorDataResult<User>("Boyle bir kullanici yok.");
		}
		
		boolean checkPassword = passwordEncoder.matches(user.getPassword(), loginedUser.getPassword());
		//loginedUser.getPassword().equals(user.getPassword())
		if(checkPassword == true){
			return new DataSuccessResult<User>(loginedUser, "Giris yapildi.");
			//this.userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())
		}
	
		return new ErrorDataResult<>("Sifre yanlis.");
		 
	}
	

	

}
