package com.jacaranda.MiTienda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.MiTienda.model.Material;
import com.jacaranda.MiTienda.service.CategoryService;
import com.jacaranda.MiTienda.service.MaterialService;

@Controller
public class MaterialController {

	private static final String REDIRECT_ITEM = "redirect:/articulo/list";

	@Autowired
	private MaterialService service;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("articulo/add")
	public String addItem(Model model) {

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("material", new Material());

		return "addMaterial";
	}

	@PostMapping("articulo/add/submit")
	public String addItemSubmit(@ModelAttribute Material material) {

		service.addItem(material);

		return REDIRECT_ITEM;
	}

	@GetMapping("/articulo/delete")
	public String deleteItem(@RequestParam Integer id, Model model) {

		model.addAttribute("material", service.getItem(id));

		return "deleteMaterial";
	}

	@PostMapping("/articulo/delete/submit")
	public String deleteItemSubmit(@ModelAttribute Material material) {

		service.deleteItem(material);

		return REDIRECT_ITEM;
	}

	@GetMapping("/articulo/update")
	public String updateItem(@RequestParam Integer id, Model model) {

		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("material", service.getItem(id));

		return "updateMaterial";
	}

	@PostMapping("/articulo/update/submit")
	public String updateItemSubmit(@ModelAttribute Material material) {

		service.updateItem(material);

		return REDIRECT_ITEM;
	}

	@GetMapping("articulo/list")
	public String itemList(Model model, @RequestParam("pageNumber")
	
		Optional<Integer> pageNumber,
		@RequestParam("sizeNumber") Optional<Integer> sizeNumber,
		@RequestParam("sortField") Optional<String> sortField,
		@RequestParam("stringFind") Optional<String> stringFind,
		@RequestParam("idCategory") Optional<Integer> idCategory) {
		

		Page<Material> page = service.findAll(pageNumber.orElse(1),
				sizeNumber.orElse(10), sortField.orElse("id"), stringFind.orElse(null), idCategory.orElse(0));
		
		model.addAttribute("currentPage",pageNumber.orElse(1));
		model.addAttribute("idCategory",idCategory.orElse(0));
		model.addAttribute("totalPages",page.getTotalPages());
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("sortField", sortField.orElse("id"));
		model.addAttribute("keyword", stringFind.orElse(null));
		model.addAttribute("materials", page.getContent());
		
		return "materialList";
		
//		List<Material> materials = service.getItems();
//		model.addAttribute("materials", materials);
//		return "materialList";
	}

}
