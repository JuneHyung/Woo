package com.fridge.model.service;

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

}
