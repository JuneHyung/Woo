package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Ingredientsdetail;

public interface IngredientsdetailRepository extends JpaRepository<Ingredientsdetail, Integer>{
	@Query("SELECT DISTINCT category FROM Ingredientsdetail")
	String [] findDistinctCategory();
	
	Ingredientsdetail [] findByCategory(String category);
	
	@Query("SELECT name From Ingredientsdetail where id = :ingredientsdetailId")
	String findIngredientsdetailNameByIngredientsdetailId(Integer ingredientsdetailId);
}
