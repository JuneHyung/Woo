package com.fridge.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Main;

public interface MainRepository extends JpaRepository<Main, Integer> {
	List<Main> findByIngredientsdetailId(int id, Pageable pagable);

	@Query(value = "select ingredientsdetail_id from main where recipe_id = :recipeId", nativeQuery = true)
	int [] findIngredientsdetailIdByRecipeId(int recipeId);
}
