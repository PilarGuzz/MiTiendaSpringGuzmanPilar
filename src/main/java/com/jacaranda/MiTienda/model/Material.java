package com.jacaranda.MiTienda.model;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity	
public class Material {

		
		@Id
		private Integer id;
		private String name;
		private String description;
		private double price;
		private int stock;
		@ManyToOne
		@JoinColumn (name="category")
		private Category category;
		
		

		

		public Material() {
			
		}




		public Material(Integer id, String name, String description, double price, int stock, Category category) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.price = price;
			this.stock = stock;
			this.category = category;
		}




		public Integer getId() {
			return id;
		}




		public void setId(Integer id) {
			this.id = id;
		}




		public String getName() {
			return name;
		}




		public void setName(String name) {
			this.name = name;
		}




		public String getDescription() {
			return description;
		}




		public void setDescription(String description) {
			this.description = description;
		}




		public double getPrice() {
			return price;
		}




		public void setPrice(double price) {
			this.price = price;
		}




		public int getStock() {
			return stock;
		}




		public void setStock(int stock) {
			this.stock = stock;
		}




		




		public Category getCategory() {
			return category;
		}




		public void setCategory(Category category) {
			this.category = category;
		}




		@Override
		public int hashCode() {
			return Objects.hash(category, description, id, price, stock, name);
		}




		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Material other = (Material) obj;
			return Objects.equals(category, other.category)
					&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
					&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && stock == other.stock
					&& Objects.equals(name, other.name);
		}




		@Override
		public String toString() {
			return "Movies [id=" + id + ", title=" + name + ", description_movie=" + description + ", price=" + price
					+ ", stock=" + stock + ", category_id=" + category + "]";
		}
		
		
		
		
		

	


}
