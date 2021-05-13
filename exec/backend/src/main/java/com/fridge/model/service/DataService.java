package com.fridge.model.service;

import com.fridge.model.Ingredientsdetail;
import com.fridge.model.Main;
import com.fridge.model.Recipe;
import com.fridge.model.Sub;

public interface DataService {

	void creteRecipe(Recipe recipe);

	void createIngredientsdetail(Ingredientsdetail ingredientsdetail);

	void createMain(Main main);

	void createSub(Sub sub);

}
