package com.jacaranda.MiTienda.service;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.MiTienda.model.User;
import com.jacaranda.MiTienda.repository.UserRepository;

@Service
public class UserService {
	
		
		@Autowired
		UserRepository repository;

		
		
		public User getUser(String id) {
			return repository.findById(id).orElse(null);
		}
		
		public List<User> getUsers() {
			return repository.findAll();
		}

		public User addUser(User user) {
			user.setPass(getMD5(user.getPass()));
			user.setAdmin(false);
			

			return repository.save(user);
		}

		public User updateUser(User modUser) {
			User user = this.getUser(modUser.getUsername());
			user.setPass(getMD5(modUser.getPass()));
			user.setName(modUser.getName());
			user.setEmail(modUser.getEmail());
			return repository.save(user);
		}
		
		public User updateAdmin(User modUser) {
			User user = this.getUser(modUser.getUsername());
			user.setAdmin(modUser.isAdmin());
			return repository.save(user);
		}

		public void deleteUser(String id) {
			repository.deleteById(id);
		}

		public static String getMD5(String input) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(input.getBytes());
				BigInteger number = new BigInteger(1, messageDigest);
				String hashtext = number.toString(16);

				while (hashtext.length() < 32) {
					hashtext = "0" + hashtext;
				}
				return hashtext;
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
		}

	


}
