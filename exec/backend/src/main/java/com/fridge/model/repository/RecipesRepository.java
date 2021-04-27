package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Recipe;

public interface RecipesRepository extends JpaRepository<Recipe, Integer>{
	
}
