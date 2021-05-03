package com.fridge.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.model.Fridge;


public interface FridgeRepository extends JpaRepository<Fridge, Integer>{
   Fridge [] findByUser_Id(int user_id);
   
   Optional<Fridge> findByIdAndUser_Id(int id, int user_id);
}