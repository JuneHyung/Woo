package com.fridge.model.service;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;
import com.fridge.model.dto.FridgeDto;
import com.fridge.model.dto.IngredientsDto;

public interface FridgeService {

	void create(Principal user, FridgeDto fridgeDto) throws Exception;

	void addIngredients(IngredientsDto ingredientsDto) throws Exception;

	Fridge[] fridgeList(int id) throws Exception;

	List<Ingredientsdetail> ingredientsDetailList() throws Exception;

	Optional<Fridge> fridgeDetail(int fridgeId) throws Exception;

	Ingredients[] ingrediantsList(int fridgeId) throws Exception;

	String[] categoryList() throws Exception;

	Ingredientsdetail[] categoryByingredientsList(String category) throws Exception;

	void fridgeDel(Principal user, int fridgeId) throws IllegalArgumentException, SQLException;

	void delIngredients(int ingredients_id) throws IllegalArgumentException;

	void moveIngredients(IngredientsDto ingredientsDto) throws IllegalArgumentException;

}
