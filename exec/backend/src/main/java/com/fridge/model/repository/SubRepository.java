package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Sub;

public interface SubRepository extends JpaRepository<Sub, Integer> {
	
	@Query(value = "select ingredientsdetail_id from sub where recipe_id = :recipeId", nativeQuery = true)
	int[] findIngredientsdetail_idByRecipe_id(int recipeId);

}
