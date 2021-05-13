package com.fridge.model.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;
import com.fridge.model.dto.FridgeDto;
import com.fridge.model.dto.IngredientsDto;

public interface FridgeService {

	void create(Principal user, FridgeDto fridgeDto) throws WrongFormException;

	void addIngredients(IngredientsDto ingredientsDto);

	Fridge[] fridgeList(int id);

	List<Ingredientsdetail> ingredientsDetailList();

	Optional<Fridge> fridgeDetail(int fridgeId) throws WrongFormException;

	Ingredients[] ingrediantsList(int fridgeId);

	String[] categoryList();

	Ingredientsdetail[] categoryByingredientsList(String category);

	void fridgeDel(Principal user, int fridgeId) throws WrongFormException;

	void delIngredients(int ingredientsId) throws WrongFormException;

	void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException;

}
