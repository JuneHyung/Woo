package com.fridge.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Ingredientsdetail;
import com.fridge.model.Main;
import com.fridge.model.Recipe;
import com.fridge.model.Sub;
import com.fridge.model.repository.IngredientsdetailRepository;
import com.fridge.model.repository.MainRepository;
import com.fridge.model.repository.RecipesRepository;
import com.fridge.model.repository.SubRepository;

@Service
public class DataServiceImpl implements DataService {
	@Autowired
	private RecipesRepository recipeRepository;
	@Autowired
	private IngredientsdetailRepository ingredientsdetailRepository;
	@Autowired
	private MainRepository mainRepository;
	@Autowired
	private SubRepository subRepository;

	@Override
	public void creteRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
	}

	@Override
	public void createIngredientsdetail(Ingredientsdetail ingredientsdetail) {
		ingredientsdetailRepository.save(ingredientsdetail);
	}

	@Override
	public void createMain(Main main) {
		mainRepository.save(main);
	}

	@Override
	public void createSub(Sub sub) {
		subRepository.save(sub);
	}

}
