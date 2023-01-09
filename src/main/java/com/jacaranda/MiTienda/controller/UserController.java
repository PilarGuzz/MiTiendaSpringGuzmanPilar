package com.jacaranda.MiTienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.MiTienda.model.User;
import com.jacaranda.MiTienda.service.UserService;

@Controller
public class UserController {

	private static final String REDIRECT_USER = "redirect:/usuario/list";

	@Autowired
	UserService service;

	@GetMapping("login")
	public String login() {

		return "login";

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

	@GetMapping("usuario/delete")
	public String deleteUser(@RequestParam("id") String username, Model model) {

		User user = service.getUser(username);
		model.addAttribute("user", user);

		return "deleteUser";
	}

	@PostMapping("usuario/delete/submit")
	public String deleteUserSubmit(@ModelAttribute User user) {

		service.deleteUser(user.getUsername());

		return REDIRECT_USER;
	}

	@GetMapping("usuario/update")
	public String updateUser(@RequestParam("id") String id, Model model) {

		User user = service.getUser(id);
		model.addAttribute("user", user);

		return "updateUser";
	}

	@PostMapping("usuario/update/submit")
	public String updateUserSubmit(@ModelAttribute User user) {

		service.updateUser(user);

		return REDIRECT_USER;
	}

	@GetMapping("usuario/admin")
	public String updateUserAdmin(@RequestParam("id") String id, Model model) {

		User user = service.getUser(id);
		model.addAttribute("user", user);

		return "updateAdmin";
	}

	@PostMapping("usuario/admin/submit")
	public String updateUserAdminSubmit(@ModelAttribute User user) {

		service.updateAdmin(user);

		return REDIRECT_USER;
	}

	@GetMapping({ "usuario/list", "/" })
	public String getUsers(Model model) {

		List<User> users = service.getUsers();
		model.addAttribute("users", users);

		return "usersList";
	}

}
