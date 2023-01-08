package com.jacaranda.MiTienda.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.MiTienda.model.Category;
import com.jacaranda.MiTienda.model.Material;
import com.jacaranda.MiTienda.repository.CategoryRepository;

@Service
public class CategoryService {
	
		
		@Autowired
		private CategoryRepository repository;

		
		public List<Category> getCategories() {
			return repository.findAll();
		}
		
		public Category getCategory(Integer id) {
			return repository.findById(id).orElse(null);
		}

		public Category addCategory(Category category) {
			Category saved = null;
			
			List<Material> listMaterial= new ArrayList<>();
			
			if (category.getId() != null && category.getName()!=null && category.getDescription() != null) {
				category.setMaterialList(listMaterial);
				saved = repository.save(category);
			}
			
			return saved;
		}
		
		public boolean deleteCategory(Category category) {
			boolean deleted = false;
			
			if(this.getCategory(category.getId()) != null) {
				
				repository.delete(category);
				deleted = true;
			}
			
			return deleted;
		}
		
		public Category updateCategory(Category categoryMod) {
			Category saved = null;
			Category cat = this.getCategory(categoryMod.getId());
			categoryMod.setMaterialList(cat.getMaterialList());
			
			if(categoryMod.getId() != null && categoryMod.getMaterialList() != null && categoryMod.getDescription() != null) {
				
				cat.setName(categoryMod.getName());
				cat.setDescription(categoryMod.getDescription());;
				saved = repository.save(cat);
			}
			
			return saved;
		}
		
		
	


}
