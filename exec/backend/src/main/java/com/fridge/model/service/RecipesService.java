package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import com.fridge.model.Main;
import com.fridge.model.Recipe;

public interface RecipesService {

	public void recipeinsert(Recipe recipe) throws Exception;

	public Optional<Recipe> recipeselect(int recipe_id) throws Exception;

	public List<Recipe> recipelist(int start, int limit) throws Exception;

	public List<Main> ingredientrecipes(int id) throws Exception;

	public void upViews(int recipe_id) throws Exception;

}
