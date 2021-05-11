package com.fridge.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fridge.model.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
	@Modifying
	@Query(value = "delete FROM subscribe where user_id = user_id and subscribe_id = subscribe_id", nativeQuery = true)
	Subscribe deletesubscribe(int userId, int subscribeId);

	List<Subscribe> findByUser_id(int userId);

	@Query(value = "select id from Subscribe sub where user_id = :userId and subscribe_id = :subscribeId")
	Optional<Integer> findByUserIdAndSubscribeId(@Param("userId") int userId, @Param("subscribeId") int subscribeId);

}
