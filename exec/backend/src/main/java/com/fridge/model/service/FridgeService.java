package com.fridge.model.service;

import java.util.List;
import java.util.Optional;

import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;


public interface FridgeService {

   void create(Fridge fridge) throws Exception;

   Fridge[] fridgeList(int id) throws Exception;

   void fridgeDel(int fridge_id) throws Exception;

   Optional <Fridge> fridgeDetail(int fridge_id) throws Exception;

   Ingredients[] ingrediantsList(int fridge_id) throws Exception;

   String [] categoryList() throws Exception;

   Ingredientsdetail [] categoryByingredientsList(String category) throws Exception;

   List<Ingredientsdetail> ingredientsDetailList() throws Exception;

   void addIngredients(Ingredients ingredients) throws Exception;

   void delIngredients(int ingredients_id) throws Exception;

}