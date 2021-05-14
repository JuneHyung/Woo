package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.Recipe;
import com.fridge.model.dto.RecipeDto;

public interface RecipesService {

	public void recipeInsert(RecipeDto recipeDto) throws WrongFormException;

	public Optional<Recipe> recipeSelect(int recipeId) throws WrongFormException;

	public List<Recipe> recipeList(int start, int limit);

	public List<Recipe> ingredientRecipes(int id, int page, int size);

	public void upViews(int recipeId) throws WrongFormException;

	public String[] recipeMain(int recipeId);

	public String[] recipeSub(int recipeId);

}
