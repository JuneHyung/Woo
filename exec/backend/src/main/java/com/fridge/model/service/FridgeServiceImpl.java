package com.fridge.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridge.model.Fridge;
import com.fridge.model.repository.FridgeRepository;

@Service
public class FridgeServiceImpl implements FridgeService{
   @Autowired
   private FridgeRepository fridgeRepository;

   @Override
   public void create(Fridge fridge) throws Exception {
      fridgeRepository.save(fridge);
   }

   @Override
   public Fridge[] fridgeList(int id) throws Exception {
      return fridgeRepository.findByUser_Id(id);
   }
}