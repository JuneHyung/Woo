package com.fridge.model.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.common.error.WrongFormException;
import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;
import com.fridge.model.dto.FridgeDto;
import com.fridge.model.dto.IngredientsDto;
import com.fridge.model.repository.FridgeRepository;
import com.fridge.model.repository.IngredientsRepository;
import com.fridge.model.repository.IngredientsdetailRepository;

@Service
public class FridgeServiceImpl implements FridgeService {
	@Autowired
	private FridgeRepository fridgeRepository;
	@Autowired
	private IngredientsRepository ingredientsRepository;
	@Autowired
	private IngredientsdetailRepository ingredientsdetailRepository;

	@Override
	public void create(Principal user, FridgeDto fridgeDto) throws WrongFormException {
		FridgeDto fridge = Optional.ofNullable(fridgeDto).orElseThrow(() -> new WrongFormException("냉장고 정보 입력은 필수입니다"));

		fridgeRepository.save(new Fridge(fridge, Integer.parseInt(user.getName())));
	}

	@Override
	public void addIngredients(IngredientsDto ingredientsDto) {
		int cnt = ingredientsRepository.findCntByIngredientsdetailId(ingredientsDto.getIngredientsDetailId(),
				ingredientsDto.getFridgeId());

		if (cnt == 0) {
			Ingredients ingredients = new Ingredients(ingredientsDto);
			ingredientsRepository.save(ingredients);
		} else {
			int id = ingredientsRepository.findByIngredientsdetailIdandFridgeId(ingredientsDto.getIngredientsDetailId(),
					ingredientsDto.getFridgeId());

			IngredientsDto ingredientsdto = new IngredientsDto(id, ingredientsDto.getExpired(),
					ingredientsDto.getLocx(), ingredientsDto.getLocy(), ingredientsDto.getFridgeId(),
					ingredientsDto.getIngredientsDetailId());
			Ingredients ingredientsSave = new Ingredients(ingredientsdto);
			ingredientsRepository.save(ingredientsSave);
		}
	}

	@Override
	public Fridge[] fridgeList(int id) {
		return fridgeRepository.findByUserId(id);
	}

	@Override
	public List<Ingredientsdetail> ingredientsDetailList() {
		return ingredientsdetailRepository.findAll();
	}

	@Override
	public Optional<Fridge> fridgeDetail(int fridgeId) throws WrongFormException {
		return Optional.ofNullable(
				fridgeRepository.findById(fridgeId).orElseThrow(() -> new WrongFormException("존재 하지 않는 냉장고 입니다")));
	}

	@Override
	public Ingredients[] ingrediantsList(int fridgeId) {
		return ingredientsRepository.findByFridgeId(fridgeId);
	}

	@Override
	public String[] categoryList() {
		return ingredientsdetailRepository.findDistinctCategory();
	}

	@Override
	public Ingredientsdetail[] categoryByingredientsList(String category) {
		return ingredientsdetailRepository.findByCategory(category);
	}

	@Override
	public void fridgeDel(Principal user, int fridgeId) throws WrongFormException {
		Fridge fridge = fridgeRepository.findByIdAndUserId(fridgeId, Integer.parseInt(user.getName()))
				.orElseThrow(() -> new WrongFormException("존재하지 않는 냉장고 입니다"));

		fridgeRepository.delete(fridge);
	}

	@Override
	public void delIngredients(int ingredientsId) throws WrongFormException {
		Ingredients ingredients = ingredientsRepository.findById(ingredientsId)
				.orElseThrow(() -> new WrongFormException("재료 번호 확인 필요"));

		ingredientsRepository.delete(ingredients);
	}

	@Override
	public void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException {
		Ingredients ingredients = Optional.ofNullable(new Ingredients(ingredientsDto))
				.orElseThrow(() -> new WrongFormException("재료의 정보 입력 필수"));

		ingredientsRepository.save(ingredients);
	}
}
