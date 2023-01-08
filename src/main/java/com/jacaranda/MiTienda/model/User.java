package com.jacaranda.MiTienda.model;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {		
			@Id
			private String username;
			private String pass;
			private String name;
			private String email;
			private boolean admin;

		public User() {
		}

		public User(String username, String password, String name, String email, boolean admin) {
			super();
			this.username = username;
			this.pass = password;
			this.name = name;
			this.email = email;
			this.admin = admin;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return pass;
		}

		public void setPassword(String password) {
			this.pass = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public boolean isAdmin() {
			return admin;
		}

		public void setAdmin(boolean admin) {
			this.admin = admin;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(admin, email, name, pass, username);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return admin == other.admin && Objects.equals(email, other.email)
					&& Objects.equals(name, other.name) && Objects.equals(pass, other.pass)
					&& Objects.equals(username, other.username);
		}

		@Override
		public String toString() {
			return "Users [username=" + username + ", name=" + name + ", email="
					+ email + ", admin=" + admin + "]";
		}

		
		
	


}
