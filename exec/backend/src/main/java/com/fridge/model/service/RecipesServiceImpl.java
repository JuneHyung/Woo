package com.fridge.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.Main;
import com.fridge.model.Recipe;
import com.fridge.model.repository.IngredientsdetailRepository;
import com.fridge.model.repository.MainRepository;
import com.fridge.model.repository.RecipesRepository;
import com.fridge.model.repository.SubRepository;

@Service
public class RecipesServiceImpl implements RecipesService {
	@Autowired
	private RecipesRepository recipesRepository;

	@Autowired
	private MainRepository mainRepository;

	@Autowired
	private IngredientsdetailRepository ingredientsdetailRepository;

	@Autowired
	private SubRepository subRepository;

	@Override
	public void recipeInsert(Recipe recipe) throws WrongFormException {
		Optional<Recipe> optRecipe = Optional.ofNullable(recipe);
		optRecipe.map(Recipe::getName).orElseThrow(() -> new WrongFormException("레시피 이름을 입력하세요"));
		optRecipe.map(Recipe::getCategory).orElseThrow(() -> new WrongFormException("레시피 카테고리를 선택하세요"));
		optRecipe.map(Recipe::getSubcategory).orElseThrow(() -> new WrongFormException("레시피 서브 카테고리를 선택하세요"));
		optRecipe.map(Recipe::getUrl).orElseThrow(() -> new WrongFormException("레시피 URL을 입력하세요"));

		recipesRepository.save(recipe);
	}

	@Override
	public Optional<Recipe> recipeSelect(int recipeId) throws WrongFormException {
		return Optional.ofNullable(
				recipesRepository.findById(recipeId).orElseThrow(() -> new WrongFormException("레시피 ID를 확인하세요")));
	}

	@Override
	public List<Recipe> recipeList(int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		return recipesRepository.findAll(pageRequest).getContent();
	}

	@Override
	public List<Recipe> ingredientRecipes(int id, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);

		List<Main> mainList = mainRepository.findByIngredientsdetailId(id, pageRequest);

		List<Recipe> recipeList = new ArrayList<>();

		for (Main main : mainList)
			recipeList.add(main.getRecipe());

		return recipeList;
	}

	@Override
	public void upViews(int recipeId) throws WrongFormException {
		Recipe r = Optional.ofNullable(recipesRepository.getOne(recipeId))
				.orElseThrow(() -> new WrongFormException("존재하지 않는 냉장고 아이디 입니다"));
		Recipe updateRecipe = new Recipe(r, r.getViews() + 1);

		recipesRepository.save(updateRecipe);
	}

	@Override
	public String[] recipeMain(int recipeId) {
		int[] ingredientsId = mainRepository.findIngredientsdetailIdByRecipeId(recipeId);
		String[] mainName = new String[ingredientsId.length];

		for (int i = 0; i < ingredientsId.length; i++) {
			mainName[i] = ingredientsdetailRepository.findIngredientsdetailNameByIngredientsdetailId(ingredientsId[i]);

		}
		return mainName;
	}

	@Override
	public String[] recipeSub(int recipeId) {
		int[] ingredientsId = subRepository.findIngredientsdetailIdByRecipeId(recipeId);
		String[] subName = new String[ingredientsId.length];
		for (int i = 0; i < ingredientsId.length; i++) {
			subName[i] = ingredientsdetailRepository.findIngredientsdetailNameByIngredientsdetailId(ingredientsId[i]);

		}
		return subName;
	}

}
