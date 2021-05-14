package com.fridge.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Fridge;

public interface FridgeRepository extends JpaRepository<Fridge, Integer> {
	Fridge[] findByUserId(int userId);

	Optional<Fridge> findByIdAndUserId(int id, int userId);
}