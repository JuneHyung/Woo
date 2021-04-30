package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import com.fridge.model.Main;
import com.fridge.model.Recipe;

public interface RecipesService {

	public void  recipeinsert(Recipe recipe);

	public Optional<Recipe> recipeselect(int recipe_id);

	public List<Recipe> recipelist();

	public List<Main> ingredientrecipes(int id);
	
}
