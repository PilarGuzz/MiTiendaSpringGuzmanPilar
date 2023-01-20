package com.jacaranda.MiTienda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jacaranda.MiTienda.service.UserService;



@Configuration
@EnableWebSecurity
public class WebSecurityConf {
	
	@Autowired
	private UserService myUserDetailService;
	
	// Indicamos que la configuración se hará a travéx del servicio.
	protected void configure(AuthenticationManagerBuilder auth) throws
	Exception {
	auth.userDetailsService(myUserDetailService);
	}
	
	// Método que usaremos más abajo
	@Bean
	public UserDetailsService userDetailsService() {
	return new UserService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	// Método que autentifica
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authProvider = new
	DaoAuthenticationProvider();
	authProvider.setUserDetailsService(userDetailsService());
	authProvider.setPasswordEncoder(passwordEncoder());
	return authProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((requests) -> { 
					requests 
					.requestMatchers("/css").permitAll()
					.requestMatchers("/usuario/**").hasAnyAuthority("ADMIN")
					.requestMatchers("/articulo/list").hasAnyAuthority("USER", "ADMIN")
					.requestMatchers("/categoria/list").hasAnyAuthority("USER", "ADMIN")
						.requestMatchers("/articulo/add").hasAuthority("ADMIN")
						.requestMatchers("/articulo/add/submit").hasAuthority("ADMIN")
						.requestMatchers("/articulo/delete").hasAuthority("ADMIN")
						.requestMatchers("/articulo/delete/submit").hasAuthority("ADMIN")
						.requestMatchers("/articulo/update").hasAuthority("ADMIN")
						.requestMatchers("/articulo/update/submit").hasAuthority("ADMIN")
						.requestMatchers("/categoria/add").hasAuthority("ADMIN")
						.requestMatchers("/categoria/add/submit").hasAuthority("ADMIN")
						.requestMatchers("/categoria/delete").hasAuthority("ADMIN")
						.requestMatchers("/categoria/delete/submit").hasAuthority("ADMIN")
						.requestMatchers("/categoria/update").hasAuthority("ADMIN")
						.requestMatchers("/categoria/update/submit").hasAuthority("ADMIN")
						.requestMatchers("/signUp").permitAll()
						.requestMatchers("/signUp/submit").permitAll()
						.requestMatchers("/verify").permitAll()
						.anyRequest().authenticated();
						}).formLogin((form) -> form
						.loginPage("/login")
						.permitAll())
						.logout((logout) -> logout.permitAll());
					return http.build();
	}


}
