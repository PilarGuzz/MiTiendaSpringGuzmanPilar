package com.jacaranda.MiTienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jacaranda.MiTienda.model.Category;
import com.jacaranda.MiTienda.model.Material;
import com.jacaranda.MiTienda.repository.MaterialRepository;

@Service
public class MaterialService {

	@Autowired
	private MaterialRepository repository;
	@Autowired
	private CategoryService catService;

	public List<Material> findAll() {
		return repository.findAll();
	}
	public Page<Material> findAll(int pageNum, int pageSize, String sortField, String stringFind, int idCategoria) {
		
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, 
				Sort.by(sortField).ascending());
		Category cat = catService.getCategory(idCategoria);
		if(stringFind == null) {
			if(idCategoria!=0) {
				return repository.findByCategory(cat, pageable);
			}else {
				return repository.findAll(pageable);
			}
		}else {
			if(idCategoria!=0) {
				return repository.findByCategory(cat, pageable);
			}else {
				return repository.findByNameLike("%"+ stringFind + "%", pageable);
			}
		}
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
		Material mat = null;
		if (material.getId() != null && material.getName() != null && material.getDescription() != null
				&& material.getStock() > 0 && material.getCategory() != null && material.getPrice() > 0) {
			mat = this.getItem(material.getId());

			mat.setName(material.getName());
			;
			mat.setDescription(material.getDescription());
			mat.setPrice(material.getPrice());
			mat.setStock(material.getStock());
			mat.setCategory(material.getCategory());
			;

			mat = repository.save(mat);
		}

		return mat;
	}

}
