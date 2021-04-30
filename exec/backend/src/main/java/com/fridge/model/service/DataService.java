package com.fridge.model.service;

import com.fridge.model.Ingredientsdetail;
import com.fridge.model.Main;
import com.fridge.model.Recipe;

public interface DataService {

	void creteRecipe(Recipe recipe) throws Exception;

	void createIngredientsdetail(Ingredientsdetail ingredientsdetail) throws Exception;

	void createMain(Main main) throws Exception;

}
