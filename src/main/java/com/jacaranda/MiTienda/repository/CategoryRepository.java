package com.jacaranda.MiTienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.MiTienda.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
