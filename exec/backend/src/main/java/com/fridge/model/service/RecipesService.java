package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import com.fridge.model.Recipe;

public interface RecipesService {

	public void recipeInsert(Recipe recipe) throws Exception;

	public Optional<Recipe> recipeSelect(int recipe_id) throws Exception;

	public List<Recipe> recipeList(int start, int limit) throws Exception;

	public List<Recipe> ingredientRecipes(int id, int page, int size) throws Exception;

	public void upViews(int recipe_id) throws Exception;

}
