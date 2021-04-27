package com.fridge.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fridge.Dto.Fridge;


public interface FridgeRepository extends JpaRepository<Fridge, Integer>{

}
