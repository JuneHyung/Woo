package com.fridge.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Ingredients;
import com.fridge.model.dto.IngredientsDto;

public interface IngredientsRepository extends JpaRepository<Ingredients, Integer>{
	Ingredients [] findByFridge_Id(int fridge_id);
}
