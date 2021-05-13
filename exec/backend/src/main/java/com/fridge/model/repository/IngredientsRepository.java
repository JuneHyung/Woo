package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer> {
	Ingredients[] findByFridgeId(int fridgeId);

	@Query(value = "SELECT COUNT(id) FROM Ingredients WHERE ingredientsdetail_id = :#{#ingredientsDetailId} and fridge_id = :#{#fridgeId}")
	int findCntByIngredientsdetailId(int ingredientsDetailId, int fridgeId);

	@Query(value = "SELECT id FROM Ingredients WHERE ingredientsdetail_id = :#{#ingredientsdetailId} and fridge_id = :#{#fridgeId}")
	int findByIngredientsdetailIdandFridgeId(int ingredientsdetailId, int fridgeId);
}
