package com.fridge.model.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Main;

public interface MainRepository extends JpaRepository<Main, Integer> {
	List<Main> findByIngredientsdetailId(int id, Pageable pagable);
}
