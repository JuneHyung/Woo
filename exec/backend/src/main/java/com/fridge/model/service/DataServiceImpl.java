package com.fridge.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Recipe;
import com.fridge.model.repository.RecipesRepository;

@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private RecipesRepository recipeRepository;
	
	@Override
	public void creteRecipe(Recipe recipe) throws Exception {
		recipeRepository.save(recipe);
	}

}
