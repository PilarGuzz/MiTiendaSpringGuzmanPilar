package com.jacaranda.MiTienda.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.MiTienda.model.Category;
import com.jacaranda.MiTienda.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Integer> {
	public Page<Material> findByNameLike(String keyword,
			Pageable pageable);
	public Page<Material> findByCategory(Category keyword,
			Pageable pageable);

}
