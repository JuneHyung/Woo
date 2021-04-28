package com.fridge.model.service;

import java.util.Optional;

import com.fridge.model.Fridge;


public interface FridgeService {

   void create(Fridge fridge) throws Exception;

   Fridge[] fridgeList(int id) throws Exception;

   void fridgeDel(int fridge_id) throws Exception;

   Optional <Fridge> fridgeDetail(int fridge_id) throws Exception;

}