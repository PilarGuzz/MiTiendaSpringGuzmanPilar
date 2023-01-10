package com.jacaranda.MiTienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.MiTienda.model.Material;
import com.jacaranda.MiTienda.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository repository;

	public List<Material> getItems() {
		return repository.findAll();
	}

	public Material getItem(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public Material addItem(Material material) {
		if (material.getName() != null && material.getDescription() != null
				&& material.getStock() > 0 && material.getPrice() > 0) {
			return repository.save(material);
		}
		return null;
	}

	public boolean deleteItem(Material material) {
		boolean deleted = false;

		if (this.getItem(material.getId()) != null) {
			repository.delete(material);
			deleted = true;
		}

		return deleted;
	}

	public Material updateItem(Material material) {
		Material movies = null;
		if (material.getId() != null && material.getName() != null && material.getDescription() != null
				&& material.getStock() > 0 && material.getCategory() != null && material.getPrice() > 0) {
			movies = this.getItem(material.getId());

			movies.setName(material.getName());
			;
			movies.setDescription(material.getDescription());
			movies.setPrice(material.getPrice());
			movies.setStock(material.getStock());
			movies.setCategory(material.getCategory());
			;

			movies = repository.save(movies);
		}

		return movies;
	}

}
