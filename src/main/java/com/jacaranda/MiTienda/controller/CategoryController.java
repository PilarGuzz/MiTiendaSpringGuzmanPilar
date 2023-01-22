package com.jacaranda.MiTienda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.MiTienda.model.Category;
import com.jacaranda.MiTienda.model.Material;
import com.jacaranda.MiTienda.service.CategoryService;

@Controller
public class CategoryController {

	private static final String REDIRECT_CATEGORY = "redirect:/categoria/list";

	@Autowired
	private CategoryService service;
	
	@GetMapping({"categoria/list", "/" })
	public String categoriesList(Model model, 
		@RequestParam("pageNumber") Optional<Integer> pageNumber,
		@RequestParam("sizeNumber") Optional<Integer> sizeNumber,
		@RequestParam("sortField") Optional<String> sortField,
		@RequestParam("stringFind") Optional<String> stringFind) {
		

		Page<Category> page = service.findAll(pageNumber.orElse(1),
				sizeNumber.orElse(10), sortField.orElse("id"), stringFind.orElse(""));
		model.addAttribute("currentPage",pageNumber.orElse(1));
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("sortField", sortField.orElse("id"));
		model.addAttribute("keyword", stringFind.orElse(""));
		model.addAttribute("categories", page.getContent());
		
		return "categoryList";
//		List<Category> categories = service.findAll();
//		model.addAttribute("categories", categories);
//		
//		return "categoryList";
		
	}

	@GetMapping("/categoria/add")
	public String addCategory(Model model) {

		model.addAttribute("category", new Category());

		return "addCategoria";
	}

	@PostMapping("categoria/add/submit")
	public String addCategorySubmit(@ModelAttribute Category category) {

		if (service.addCategory(category) != null) {
			return REDIRECT_CATEGORY;
		}

		return "redirect:/categoria/add";
	}

	@GetMapping("/categoria/delete")
	public String deleteCategory(@RequestParam("id") Integer id, Model model) {

		model.addAttribute("category", service.getCategory(id));

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

		model.addAttribute("category", service.getCategory(id));

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
