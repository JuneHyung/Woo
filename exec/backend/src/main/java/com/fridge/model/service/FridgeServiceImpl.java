package com.fridge.model.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Fridge;
import com.fridge.model.Ingredients;
import com.fridge.model.Ingredientsdetail;
import com.fridge.model.User;
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
	public void create(Principal user, FridgeDto fridgeDto) throws Exception {
		Fridge fridge = new Fridge(fridgeDto, Integer.parseInt(user.getName()));

		fridgeRepository.save(fridge);
	}

	@Override
	public void addIngredients(IngredientsDto ingredientsDto) throws Exception {
		Ingredients ingredients = new Ingredients(ingredientsDto);

		ingredientsRepository.save(ingredients);
	}

	@Override
	public Fridge[] fridgeList(int id) throws Exception {
		return fridgeRepository.findByUser_Id(id);
	}

	@Override
	public List<Ingredientsdetail> ingredientsDetailList() throws Exception {
		return ingredientsdetailRepository.findAll();
	}

	@Override
	public Optional<Fridge> fridgeDetail(int fridge_id) throws Exception {
		return fridgeRepository.findById(fridge_id);
	}

	@Override
	public Ingredients[] ingrediantsList(int fridge_id) throws Exception {
		return ingredientsRepository.findByFridge_Id(fridge_id);
	}

	@Override
	public String[] categoryList() throws Exception {
		return ingredientsdetailRepository.findDistinctCategory();
	}

	@Override
	public Ingredientsdetail[] categoryByingredientsList(String category) throws Exception {
		return ingredientsdetailRepository.findByCategory(category);
	}

	@Override
	public void fridgeDel(Principal user, int fridge_id) throws Exception {
		Optional<Fridge> fridge = fridgeRepository.findByIdAndUser_Id(fridge_id, Integer.parseInt(user.getName()));

		if (!fridge.isPresent())
			throw new Exception("삭제 실패!!");

		fridgeRepository.delete(fridge.get());
	}

	@Override
	public void delIngredients(int ingredients_id) throws Exception {
		ingredientsRepository.deleteById(ingredients_id);

	}

	@Override
	public void moveIngredients(IngredientsDto ingredientsDto) throws Exception {
		System.out.println(ingredientsDto.toString());
		Ingredients ingredients = new Ingredients(ingredientsDto);
	
		ingredientsRepository.save(ingredients);
	}
}
