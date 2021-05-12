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
		Optional<FridgeDto> optFridgeDto = Optional.ofNullable(fridgeDto);
		optFridgeDto.map(FridgeDto::getName).orElseThrow(() -> new WrongFormException("냉장고 이름 입력은 필수입니다"));
		optFridgeDto.map(FridgeDto::getType).orElseThrow(() -> new WrongFormException("냉장고 타입 입력은 필수입니다"));

		Fridge fridge = new Fridge(fridgeDto, Integer.parseInt(user.getName()));

		fridgeRepository.save(fridge);
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
		return fridgeRepository.findByUser_Id(id);
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
		return ingredientsRepository.findByFridge_Id(fridgeId);
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
		Optional<Fridge> fridge = fridgeRepository.findByIdAndUser_Id(fridgeId, Integer.parseInt(user.getName()));
		fridge.orElseThrow(() -> new WrongFormException("존재하지 않는 냉장고 입니다"));

		fridgeRepository.delete(fridge.get());
	}

	@Override
	public void delIngredients(int ingredientsId) throws WrongFormException {
		Optional<Ingredients> ingredients = ingredientsRepository.findById(ingredientsId);
		ingredients.orElseThrow(() -> new WrongFormException("재료 번호 확인 필요"));
		
		ingredientsRepository.deleteById(ingredientsId);
	}

	@Override
	public void moveIngredients(IngredientsDto ingredientsDto) throws WrongFormException {
		Optional<Ingredients> ingredients = Optional.ofNullable(new Ingredients(ingredientsDto));
		ingredients.map(Ingredients::getLocx).orElseThrow(() -> new WrongFormException("재료의 x좌표 필수"));
		ingredients.map(Ingredients::getLocy).orElseThrow(() -> new WrongFormException("재료의 y좌표 필수"));
		ingredients.map(Ingredients::getFridge).orElseThrow(() -> new WrongFormException("재료를 저장할 냉장고 번호는 필수"));
		ingredients.map(Ingredients::getIngredientsdetail).orElseThrow(() -> new WrongFormException("재료의 디테일 정보는 필수"));

		ingredientsRepository.save(ingredients.get());
	}
}
