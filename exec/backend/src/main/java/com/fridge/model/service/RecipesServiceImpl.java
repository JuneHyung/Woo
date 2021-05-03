package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Main;
import com.fridge.model.Recipe;
import com.fridge.model.repository.MainRepository;
import com.fridge.model.repository.RecipesRepository;

@Service
public class RecipesServiceImpl implements RecipesService{
	@Autowired
	private RecipesRepository recipesrepository;
	
	@Autowired
	private MainRepository mainrepository;
	
	@Override
	public void recipeinsert(Recipe recipe) {
		// TODO Auto-generated method stub
		recipesrepository.save(recipe);
	}

	@Override
	public Optional<Recipe> recipeselect(int recipe_id) {
		return recipesrepository.findById(recipe_id);
	}

	@Override
	public List<Recipe> recipelist() {
		// TODO Auto-generated method stub
		return recipesrepository.findAll();
	}

	@Override
	public List<Main> ingredientrecipes(int id) {
		// TODO Auto-generated method stub
		return mainrepository.findByIngredientsdetail_id(id);
	}

	@Override
	public void upViews(int recipe_id) throws Exception {
		Recipe r = recipesrepository.getOne(recipe_id);
		int views = r.getViews();
		r.setViews(views + 1);
		recipesrepository.save(r);
		
	}

}
