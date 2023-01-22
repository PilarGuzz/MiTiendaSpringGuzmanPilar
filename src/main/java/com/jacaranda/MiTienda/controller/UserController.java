package com.jacaranda.MiTienda.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.MiTienda.model.User;
import com.jacaranda.MiTienda.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserController {

	private static final String REDIRECT_USER = "redirect:/usuario/list";

	@Autowired
	UserService service;

	@GetMapping("/login")
	public String login() {
		return "login";

	}
	
	@GetMapping("/signUp")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signUp";
	}
	
	@GetMapping("/signUp/submit")
	public String processRegister(@ModelAttribute("user") User user, HttpServletRequest request){
		
		String siteURL = request.getRequestURL().toString();
		siteURL = siteURL.replace(request.getServletPath(), "");
		user.setRole("USER");
		
		if(service.checkExist(user)) {
			try {
			service.add(user, siteURL);
			}catch (UnsupportedEncodingException e) {
				return "error";
			}catch (MessagingException e) {
				return "error";
			}
			return "registerSuccess";
		}else
			return "error";
	}
	
	@GetMapping("logout")
	public String logout() {
		return "logout";
	}

	@GetMapping("/usuario/add")
	public String addUser(Model model) {
		User user = new User();

		model.addAttribute("user", user);

		return "AddUsers";
	}

	@PostMapping("usuario/add/submit")
	public String addUserSubmit(@ModelAttribute User user) {

		service.addUser(user);

		return REDIRECT_USER;
	}

	@GetMapping("/usuario/delete")
	public String deleteUser(@RequestParam("username") String username, Model model) {

		User user = service.getUser(username);
		model.addAttribute("user", user);

		return "deleteUser";
	}

	@PostMapping("usuario/delete/submit")
	public String deleteUserSubmit(@ModelAttribute User user) {

		service.deleteUser(user.getUsername());

		return"redirect:/categoria/list";
	}

	@GetMapping("usuario/update")
	public String updateUser(@RequestParam("username") String username, Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User logged = (User) auth.getPrincipal();
		User user = service.getUser(username);
		if(logged.getRole().equals("ADMIN") || (logged.getRole().equals("USER")  && logged.getUsername().equals(username))) {
			
			model.addAttribute("user", user);
	
			return "updateUser";
		}
		return "error";
	}

	@PostMapping("usuario/update/submit")
	public String updateUserSubmit(@ModelAttribute User user) {

		service.updateUser(user);

		return REDIRECT_USER;
	}

	@GetMapping("usuario/admin")
	public String updateUserAdmin(@RequestParam("username") String id, Model model) {

		User user = service.getUser(id);
		model.addAttribute("user", user);

		return "updateAdmin";
	}

	@PostMapping("usuario/admin/submit")
	public String updateUserAdminSubmit(@ModelAttribute User user) {

		service.updateAdmin(user);

		return REDIRECT_USER;
	}

	@GetMapping("usuario/list")
	public String getUsers(Model model) {

		List<User> users = service.getUsers();
		model.addAttribute("users", users);

		return "usersList";
	}
	

	
	@GetMapping("/verify")
	public String verification(@RequestParam("code") String code, @RequestParam("username")String username) {

		if(service.checkVerify(code, username)) {
			return "verificationSuccess";
	
		}else {
			return "error";
		}

	}
	
	@GetMapping("/error403")
	public String showError() {
		return"error";
	}

}
