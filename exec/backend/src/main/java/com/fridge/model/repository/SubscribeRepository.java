package com.fridge.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fridge.model.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	@Modifying
	@Query(value = "delete FROM subscribe where user_id = user_id and subscribe_id = subscribe_id" , nativeQuery = true)
	Subscribe deletesubscribe(int user_id, int subscribe_id);
	
	
	@Query(value = "SELECT subscribe_id FROM subscribe where id = id", nativeQuery = true)
	List<Integer> finduserid(int id);
	
}
