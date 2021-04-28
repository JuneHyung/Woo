package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer>{
	Ingredients [] findByFridge_Id(int fridge_id);
}
