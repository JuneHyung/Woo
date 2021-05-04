package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer>{
	Ingredients [] findByFridge_Id(int fridge_id);

	@Query(value = "SELECT COUNT(id) FROM Ingredients WHERE ingredientsdetail_id = :#{#ingredientsDetailId}")
	int findCntByIngredientsdetailId(int ingredientsDetailId);

	Ingredients findByIngredientsdetailId(int ingredientsdetail_id);
}
