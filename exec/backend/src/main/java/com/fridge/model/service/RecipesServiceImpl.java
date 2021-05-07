package com.fridge.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fridge.model.Main;
import com.fridge.model.Recipe;
import com.fridge.model.repository.MainRepository;
import com.fridge.model.repository.RecipesRepository;

@Service
public class RecipesServiceImpl implements RecipesService {
	@Autowired
	private RecipesRepository recipesRepository;

	@Autowired
	private MainRepository mainRepository;

	@Override
	public void recipeInsert(Recipe recipe) throws Exception {
		recipesRepository.save(recipe);
	}

	@Override
	public Optional<Recipe> recipeSelect(int recipe_id) throws Exception {
		return recipesRepository.findById(recipe_id);
	}

	@Override
	public List<Recipe> recipeList(int page, int size) throws Exception {
		PageRequest pageRequest = PageRequest.of(page, size);
		return recipesRepository.findAll(pageRequest).getContent();
	}

	@Override
	public List<Recipe> ingredientRecipes(int id, int page, int size) throws Exception {
		PageRequest pageRequest = PageRequest.of(page, size);
		
		List<Main> mainList =  mainRepository.findByIngredientsdetail_id(id, pageRequest);
		
		List<Recipe> recipeList = new ArrayList<Recipe>();
		
		for(Main main : mainList)
			recipeList.add(main.getRecipe());
		
		return recipeList;
	}

	@Override
	public void upViews(int recipe_id) throws Exception {
		Recipe r = recipesRepository.getOne(recipe_id);
		Recipe updateRecipe = new Recipe(r, r.getViews() + 1);

		recipesRepository.save(updateRecipe);
	}

}
