package com.example.todotodo.security.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.todotodo.entities.concretes.Role;
import com.example.todotodo.service.abstracts.AuthService;
import com.example.todotodo.service.concretes.AuthManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		authenticationProvider.setUserDetailsService(authService);
		
		return authenticationProvider;
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception
    {
		http.cors();
		http.csrf().disable()
		.authorizeRequests()
		
		.antMatchers("/api/admin/**").permitAll()
		.antMatchers("/api/todo/**").permitAll()
		.antMatchers("/api/auth/**").permitAll()
		.anyRequest()
		.authenticated();
		
		
		
		
//        http.cors();
//        http.csrf().disable();
//        
//        http.authorizeRequests()
//        .antMatchers("/api/admin/**").hasRole(Role.ADMIN.name()).anyRequest().authenticated();
//        
//        http.authorizeRequests()
//        .antMatchers("/api/auth/**").permitAll()
//        .antMatchers("/api/admin/**").permitAll()
//        .antMatchers("/api/todo/**").permitAll()
//        .anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
	
}
