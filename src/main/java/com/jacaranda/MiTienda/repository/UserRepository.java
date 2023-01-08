package com.jacaranda.MiTienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.MiTienda.model.User;

public interface UserRepository extends JpaRepository<User, String> {

			
		
}
