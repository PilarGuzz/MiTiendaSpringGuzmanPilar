package com.jacaranda.MiTienda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.MiTienda.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	List<User> findByEmail(String email);
	User findByUsername(String username);
			
		
}
