package com.jacaranda.MiTienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.MiTienda.model.Category;
import com.jacaranda.MiTienda.service.CategoryService;

@Controller
public class CategoryController {

	private static final String REDIRECT_CATEGORY = "redirect:/categoria/list";
	private static final String MODEL_NAME = "category";

	@Autowired
	private CategoryService service;

	@GetMapping("categoria/list")
	public String categoriesList(Model model) {

		List<Category> categories = service.getCategories();
		model.addAttribute("categories", service.getCategories());

		return "categoryList";
	}

	@GetMapping("/categoria/add")
	public String addCategory(Model model) {

		model.addAttribute(MODEL_NAME, new Category());

		return "addCategoria";
	}

	@PostMapping("/categoria/add/submit")
	public String addCategorySubmit(@ModelAttribute Category category) {

		if (service.addCategory(category) != null) {
			return REDIRECT_CATEGORY;
		}

		return "redirect:/categoria/add";
	}

	@GetMapping("/categoria/delete")
	public String deleteCategory(@RequestParam("id") Integer id, Model model) {

		model.addAttribute(MODEL_NAME, service.getCategory(id));

		return "deleteCategory";
	}

	@PostMapping("categoria/delete/submit")
	public String deleteCategorySubmit(@ModelAttribute Category category) {

		if (service.deleteCategory(category)) {
			return REDIRECT_CATEGORY;
		}

		return "redirect:/categoria/delete";
	}

	@GetMapping("/categoria/update")
	public String updateCategory(@RequestParam("id") Integer id, Model model) {

		model.addAttribute(MODEL_NAME, service.getCategory(id));

		return "updateCategory";
	}

	@PostMapping("categoria/update/submit")
	public String updateCategorySubmit(@ModelAttribute Category category) {

		if (service.updateCategory(category) != null) {
			return REDIRECT_CATEGORY;
		}

		return "redirect:/categoria/update";
	}

}
