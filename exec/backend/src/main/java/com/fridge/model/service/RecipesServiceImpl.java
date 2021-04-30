package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Recipe;
import com.fridge.model.repository.RecipesRepository;

@Service
public class RecipesServiceImpl implements RecipesService{
	@Autowired
	private RecipesRepository recipesrepository;
	
	@Override
	public void recipeinsert(Recipe recipe) {
		// TODO Auto-generated method stub
		recipesrepository.save(recipe);
	}

	@Override
	public Optional<Recipe> recipeselect(int recipe_id) {
		// TODO Auto-generated method stub
		return recipesrepository.findById(recipe_id);
	}

	@Override
	public List<Recipe> recipelist() {
		// TODO Auto-generated method stub
		return recipesrepository.findAll();
	}

}
