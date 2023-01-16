package com.jacaranda.MiTienda.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails {		
			@Id
			private String username;
			private String pass;
			private String name;
			private String email;
			private boolean admin;
			private String role;
			private boolean enabled;
			private String verificationCode;

		public User() {
		}

		public User(String username, String password, String name, String email, boolean admin) {
			super();
			this.username = username;
			this.pass = password;
			this.name = name;
			this.email = email;
			this.admin = admin;
			this.enabled = false;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String password) {
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
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			 authorities.add(new SimpleGrantedAuthority(this.role));
			return authorities;
		}

		@Override
		public String getPassword() {
			return this.pass;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return this.enabled;
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
			return Objects.equals(email, other.email)
					&& Objects.equals(username, other.username);
		}
		
		@Override
		public String toString() {
			return "Users [username=" + username + ", name=" + name + ", email="
					+ email + ", admin=" + admin + "]";
		}
	


}
