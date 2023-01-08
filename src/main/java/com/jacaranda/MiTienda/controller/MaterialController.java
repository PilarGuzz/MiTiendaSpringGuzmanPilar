package com.jacaranda.MiTienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacaranda.MiTienda.model.Material;
import com.jacaranda.MiTienda.model.User;
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

		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("movie", new Material());

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

		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("material", service.getItem(id));

		return "updateMaterial";
	}

	@PostMapping("/articulo/update/submit")
	public String updateItemSubmit(@ModelAttribute Material material) {

		service.updateItem(material);

		return REDIRECT_ITEM;
	}

	@GetMapping("articulo/list")
	public String itemList(Model model) {
		List<Material> materials = service.getItems();
		model.addAttribute("materials", materials);

		return "materialList";
	}

}
