package com.example.todotodo.service.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.example.todotodo.entities.concretes.User;
import com.example.todotodo.entities.concretes.dtos.UserWithRoleDto;
import com.example.todotodo.repository.abstracts.AdminRepository;
import com.example.todotodo.repository.abstracts.UserRepository;
import com.example.todotodo.service.abstracts.AdminService;

@Service
public class AdminManager implements AdminService {
	
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public DataResult<User> addUser(User user) {
		User tmp = new User();
		tmp = user;
		if(this.adminRepository.findByUsername(user.getUsername()) == null) {
			return new ErrorDataResult<>("Bu kullanici sistemde var.");
		}
		tmp.setPassword(passwordEncoder.encode(user.getPassword()));
		return new DataSuccessResult<User>(this.adminRepository.save(tmp), "User eklendi.");
	}

    @Override
    public Optional<User> findByUsername(String username)
    {
        return adminRepository.findByUsername(username);
    }

	@Override
	public Result deleteUser(Long id) {
		User tmpUser = this.adminRepository.findById(id).orElse(null);
		if(tmpUser == null) {
			return new ErrorResult("Boyle bir kullanici yok.");
		}
		
		this.adminRepository.deleteById(id);
		return new SuccessResult("User silindi.");
	}

	@Override
	public DataResult<User> updateUser(UserWithRoleDto user) {
		User newUser = this.adminRepository.findById(user.getId()).orElse(null);
		if(newUser != null) {
			
			newUser.setFullName(user.getFullName());
			//newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			//newUser.setUsername(user.getUsername());
			newUser.setRole(user.getRole());
			
			return new DataSuccessResult<User>(this.adminRepository.save(newUser),"User guncellendi.");
		}
		return new ErrorDataResult<>("Boyle bir kullanici yok.");
	}

	@Override
	public DataResult<List<User>> getUsers() {
		return new DataSuccessResult<List<User>>(this.adminRepository.findAll(), "Kullanicilar getirildi.");
	}
	
	@Override
	public DataResult<User> findByUsernameAndPassword(String username, String password) {
		User newUser = this.adminRepository.findByUsername(username).orElse(null);
	 
		if(newUser == null) {
			return new ErrorDataResult<>("Giris basarisiz.");
		}
		
		if(newUser.getPassword().equals(password)) {
			return new DataSuccessResult<User>(this.adminRepository.findByUsernameAndPassword(username, password), "Basariyla giris yapildi.");

		}
		return new ErrorDataResult<>("Giris basarisiz.");

	}


	@Override
	public DataResult<List<User>> findAllByUsernameDesc() {
		return new DataSuccessResult<List<User>>(this.adminRepository.findAllByOrderByUsernameDesc(), "");
	}


	@Override
	public DataResult<List<User>> findAllByUsernameAsc() {
		return new DataSuccessResult<List<User>>(this.adminRepository.findAllByOrderByUsernameAsc(), "");

	}


	@Override
	public DataResult<List<User>> findAllByFullnameDesc() {
		return new DataSuccessResult<List<User>>(this.adminRepository.findAllByOrderByFullNameDesc(), "");
	}


	@Override
	public DataResult<List<User>> findAllByFullnameAsc() {
		return new DataSuccessResult<List<User>>(this.adminRepository.findAllByOrderByFullNameAsc(), "");

	}


	@Override
	public DataResult<User> findByUserId(Long id) {
		User tmpUser = this.adminRepository.findById(id).orElse(null);
		return new DataSuccessResult<User>(tmpUser,"User getirildi.");
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = adminRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User cannot found!"));
	}
}
